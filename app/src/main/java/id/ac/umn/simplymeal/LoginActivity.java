package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity  {
    private EditText usrEmail, usrPass;
    private Button btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usrEmail = (EditText)findViewById(R.id.login_email);
        usrPass = (EditText)findViewById(R.id.login_pass);
        btn_login =  (Button)findViewById(R.id.btnLogin);
        btn_register =  (Button)findViewById(R.id.btnRegister);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedStore();

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisScreen = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regisScreen);
            }
        });
    }

    private void sharedStore() {


        usrEmail.setError(null);
        usrPass.setError(null);
        View fokus = null;
        boolean cancel = false;

        
        String email = usrEmail.getText().toString();
        String pass = usrPass.getText().toString();
        
        if (TextUtils.isEmpty(email)){
            usrEmail.setError("This field is required");
            fokus =  usrEmail;
            cancel = true;
        }else if(!cekUser(email)){
            usrEmail.setError("This Email is not found");
            fokus = usrEmail;
            cancel = true;
        }

        if (TextUtils.isEmpty(pass)){
            usrPass.setError("This field is required");
            fokus = usrPass;
            cancel = true;
        }else if (!cekPassword(pass)){
            usrPass.setError("This password is incorrect");
            fokus = usrPass;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else homeScreen();

    }

    private void homeScreen() {
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredEmail(getBaseContext()), Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    private boolean cekPassword(String pass) {
       return pass.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String email) {
        return email.equals(Preferences.getRegisteredEmail(getBaseContext()));
    }


}
