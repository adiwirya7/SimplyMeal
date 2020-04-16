
        package id.ac.umn.simplymeal.CategoryView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import id.ac.umn.simplymeal.R;

        import android.os.Bundle;
        import android.util.Log;

        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class MenuListActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    private RecyclerView recyclerView;
    private AdapterCardView adapter;
    private List<Menu> menuList;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String categoryId = getIntent().getStringExtra("categoryName");
        // HEADER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FOOTER NAVIGATION
        navigation = findViewById(R.id.bottom_navigation);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3,GridLayoutManager.VERTICAL,false));

        // panggil data firebase
        menuList = new ArrayList<Menu>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Menu");


        Query query = FirebaseDatabase.getInstance().getReference("Menu").orderByChild("idCategory/"+categoryId).equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnap: dataSnapshot.getChildren()){
                    String namamenu = String.valueOf(dataSnap.child("menuName").getValue());
                    Log.i("dataMenu",namamenu);
                    String menuKey = dataSnap.getKey();
                    Log.i("dataCat", menuKey);
                    Menu menus = dataSnap.getValue(Menu.class);
                    menuList.add(menus);
                }
                adapter = new AdapterCardView(MenuListActivity.this,menuList);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("dataCatCancel", "Tidak berhasil");
            }
        });

    }

}