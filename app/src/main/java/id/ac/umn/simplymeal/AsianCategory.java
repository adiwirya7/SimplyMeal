package id.ac.umn.simplymeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import id.ac.umn.simplymeal.CategoryView.AdapterCardView;

public class AsianCategory extends AppCompatActivity {
    BottomNavigationView navigation;
    RecyclerView recyclerView;
    AdapterCardView adapter;
    ArrayList<String> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asian_category);

        // HEADER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FOOTER NAVIGATION
        navigation = findViewById(R.id.bottom_navigation);

        menus = new ArrayList<>();
        menus.add("First Cardview items");
        menus.add("Second Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");
        menus.add("Third Cardview items");

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new AdapterCardView(this, menus);
        recyclerView.setAdapter(adapter);
    }
}
