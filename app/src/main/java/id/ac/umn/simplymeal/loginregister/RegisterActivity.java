package id.ac.umn.simplymeal.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.umn.simplymeal.R;


public class RegisterActivity extends AppCompatActivity {

    private EditText newusrUsername,newusrEmail, newusrPass, rePass,newusrPhone,newusrFirstName, newusrLastName;
    private FirebaseAuth mAuth;
    private Button btn_register;
    private Users userData;
    private FirebaseDatabase databaseUser;
    private DatabaseReference refs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance();
        refs = databaseUser.getReference().child("Users");

        userData = new Users();

        newusrUsername = (EditText) findViewById(R.id.register_username);
        newusrFirstName = (EditText)findViewById(R.id.register_firstName);
        newusrLastName = (EditText)findViewById(R.id.register_lastName);
        newusrEmail = (EditText) findViewById(R.id.register_email);
        newusrPass = (EditText) findViewById(R.id.register_pass);
        newusrPhone = (EditText) findViewById(R.id.register_phone);
        rePass = (EditText) findViewById(R.id.register_repass);
        rePass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    sharedStore();
                    return true;
                }
                return false;
            }
        });

        btn_register = findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedStore();

            }
        });
    }

    private  void sharedStore(){
        newusrUsername.setError(null);
        newusrEmail.setError(null);
        newusrPass.setError(null);
        rePass.setError(null);
        View fokus = null;
        boolean cancel = false;

        final String newUsernm = newusrUsername.getText().toString();
        final String newEmail = newusrEmail.getText().toString();
        final String newFirstName = newusrFirstName.getText().toString();
        final String newLastName = newusrLastName.getText().toString();
        final String newNumber = newusrPhone.getText().toString();
        final String newPass = newusrPass.getText().toString();
        String rePassword = rePass.getText().toString();


        if(TextUtils.isEmpty(newEmail)){
            newusrEmail.setError("This Field is required!");
        }
//        else if (cekEmail(newEmail)){
//            newusrEmail.setError("This Email  is already exist!");
//            fokus = newusrEmail;
//            cancel = true;
//        }
//        if(TextUtils.isEmpty(newUsernm)){
//            newusrUsername.setError("This Field is required!");
//
//        }
//        else if (cekUserName(newUsernm)){
//            newusrUsername.setError("This Email  is already exist!");
//            fokus = newusrUsername;
//            cancel = true;
//        }
//
//        if(TextUtils.isEmpty(newPass)){
//            newusrPass.setError("This Field is required!");
//        }
//        else if (!cekPass(newPass,rePassword)){
//            newusrPass.setError("This Password is Incorrect!");
//            fokus = newusrPass;
//            cancel = true;
//        }

        if(cancel){
            fokus.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(newEmail,newPass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent loginScreen = new Intent(RegisterActivity.this, LoginActivity.class);
                        userData.setEmailUser(newEmail);
                        userData.setUserName(newUsernm);
                        userData.setFirstName(newFirstName);
                        userData.setLastName(newLastName);
                        userData.setPassUser(newPass);
                        userData.setPhoneNumber(newNumber);
                        userData.setImage("");
                        userData.setAddress("");
                        userData.setBirthOfDate("");
                        userData.setGender("");

                        refs.child(newUsernm).setValue(userData);
                        startActivity(loginScreen);
                    }
                }
            });


        }

//        SharedPreferences.Editor editor = pref.edit();
//
//        editor.putString(newUsernm + newPass + "data", newUsernm + "\n" + newEmail);
//        editor.commit();

//        Intent loginAct = new Intent(RegisterActivity.this, LoginActivity.class);
//        startActivity(loginAct);
    }

    private boolean cekEmail(String newEmail) {
        return newEmail.equals(mAuth.getCurrentUser().getEmail());
    }
    private boolean cekUserName(String newUserName) {
        return newUserName.equals(mAuth.getCurrentUser().getEmail());
    }
    private boolean cekPass(String newPass, String rePassword) {
        return newPass.equals(rePassword);
    }


}

