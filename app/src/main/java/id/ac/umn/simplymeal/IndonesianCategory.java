package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IndonesianCategory extends AppCompatActivity {
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indonesian_category);

        // HEADER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FOOTER NAVIGATION
        navigation = findViewById(R.id.bottom_navigation);
    }
}
