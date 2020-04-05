package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    private EditText newusrUsername,newusrEmail, newusrPass, rePass;
    private Button  btn_register;
    private TextView back_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = (Button) findViewById(R.id.btnRegister);
        newusrUsername = (EditText) findViewById(R.id.register_username);
        newusrEmail = (EditText) findViewById(R.id.register_email);
        newusrPass = (EditText) findViewById(R.id.register_pass);
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
        back_login = (TextView) findViewById(R.id.back_login);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedStore();

            }
        });
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(back);
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

        String newUsernm = newusrUsername.getText().toString();
        String newEmail = newusrEmail.getText().toString();
        String newPass = newusrPass.getText().toString();
        String rePassword = rePass.getText().toString();
        if(TextUtils.isEmpty(newUsernm)){
            newusrUsername.setError("This Field is required!");
        }
        else if (cekUser(newUsernm)){
            newusrUsername.setError("This Username is already exist!");
            fokus = newusrUsername;
            cancel = true;
        }

        if(TextUtils.isEmpty(newEmail)){
            newusrEmail.setError("This Field is required!");
        }
        else if (cekEmail(newEmail)){
            newusrEmail.setError("This Email is already exist!");
            fokus = newusrEmail;
            cancel = true;
        }

        if(TextUtils.isEmpty(newPass)){
            newusrPass.setError("This Field is required!");
        }
        else if (!cekPass(newPass,rePassword)){
            newusrPass.setError("This Password is Incorrect!");
            fokus = newusrPass;
            cancel = true;
        }

        if(cancel){
            fokus.requestFocus();
        }
        else{
            Preferences.setRegisteredUser(getBaseContext(),newUsernm);
            Preferences.setRegisteredEmail(getBaseContext(),newEmail);
            Preferences.setRegisteredPass(getBaseContext(),newPass);
            Intent homeScreen = new Intent(RegisterActivity.this, LoginActivity.class);

            finish();
            startActivity(homeScreen);
        }

//        SharedPreferences.Editor editor = pref.edit();
//
//        editor.putString(newUsernm + newPass + "data", newUsernm + "\n" + newEmail);
//        editor.commit();

//        Intent loginAct = new Intent(RegisterActivity.this, LoginActivity.class);
//        startActivity(loginAct);
    }

    private boolean cekEmail(String newEmail) {
        return newEmail.equals(Preferences.getRegisteredEmail(getBaseContext()));
    }

    private boolean cekPass(String newPass, String rePassword) {
        return newPass.equals(rePassword);
    }

    private boolean cekUser(String newUsernm) {
        return newUsernm.equals(Preferences.getRegisteredUser(getBaseContext()));
    }


}
