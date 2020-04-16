package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;
import id.ac.umn.simplymeal.loginregister.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private int waktu_loading = 4000;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.transition);
        logo.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent homeScreen=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeScreen);
                finish();

            }
        },waktu_loading);

    }
}
