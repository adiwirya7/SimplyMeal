package id.ac.umn.simplymeal.CategoryView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.umn.simplymeal.R;

public class DetailMenu_Asian extends AppCompatActivity {
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu__asian);

        // HEADER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FOOTER NAVIGATION
        navigation = findViewById(R.id.bottom_navigation);
    }
}
