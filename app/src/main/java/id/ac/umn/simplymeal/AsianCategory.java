package id.ac.umn.simplymeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import id.ac.umn.simplymeal.CategoryView.Menu;
import id.ac.umn.simplymeal.CategoryView.MenuViewHolder;

public class AsianCategory extends AppCompatActivity {
    BottomNavigationView navigation;
    RecyclerView recyclerView;
    DatabaseReference mDatabase;
//    AdapterCardView adapter;

    ArrayList<String> menus;
    FirebaseRecyclerOptions<Menu>options;
    FirebaseRecyclerAdapter<Menu, MenuViewHolder> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // HEADER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // FOOTER NAVIGATION
        navigation = findViewById(R.id.bottom_navigation);

        // DATABASE
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Menu");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = mDatabase.orderByChild("idCategory/C01");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    String data = dataSnapshot.getValue(Menu.class).getIdMenu().toString();
//                    Log.i("Contoh Data",data);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        final String mCategory = "C01";

        options = new FirebaseRecyclerOptions.Builder<Menu>().setIndexedQuery(query,mDatabase, Menu.class).build();
        mAdapter = new FirebaseRecyclerAdapter<Menu, MenuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Menu model) {
                holder.menu_title.setText(model.getIdMenu());
                //Log.i("Data",model.getIdMenu());
            }

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_menu,parent,false);

                return new MenuViewHolder(view);
            }
        };

        mAdapter.startListening();
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
//        adapter = new AdapterCardView(this, menus);
//
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//            mAdapter.startListening();
//
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mAdapter!= null){
//            mAdapter.stopListening();
//        }
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(mAdapter!= null){
//            mAdapter.startListening();
//        }
//    }



}
