package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;
import id.ac.umn.simplymeal.loginregister.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  TextView infoUsr;
    private Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoUsr = (TextView) findViewById(R.id.display);
        infoUsr.setText(Preferences.getLoggedInUser(getBaseContext()));

        logOut = (Button)findViewById(R.id.btnLogout);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });


    }
}
