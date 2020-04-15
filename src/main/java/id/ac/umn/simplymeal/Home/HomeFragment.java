package id.ac.umn.simplymeal.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import id.ac.umn.simplymeal.R;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    CarouselView carouselView;
    private DatabaseReference mDatabase;
    private ImageView catImageView;
    private View    catName;
    private RecyclerView catRecycler;
    private AdapterCategory adapter;
    private List<Category> catList;
    private ViewPager catView;

    //int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Pindah fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.i("bla","haha");
        //view.findViewById(R.id.shimmerCategory).setVisibility(view.VISIBLE);

        catView = view.findViewById(R.id.viewPagerHead);
        catRecycler = view.findViewById(R.id.recyclerCategory);
       // ButterKnife.bind(this,view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        catRecycler.setHasFixedSize(true);
        catRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2,GridLayoutManager.VERTICAL, false));
        catRecycler.setClipToPadding(false);
        catRecycler.setNestedScrollingEnabled(true);
        //get Data
         catList= new ArrayList<>();
         mDatabase = FirebaseDatabase.getInstance().getReference().child("Category");

        Query query = mDatabase.orderByChild("idCategory");


       query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("Snap", dataSnapshot.getKey());
                if(dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        String menuKey = dataSnapshot1.getKey();
                        Log.i("dataCat", menuKey);
                        Category category = dataSnapshot1.getValue(Category.class);
                        catList.add(category);
                    }
                    view.findViewById(R.id.shimmerCategory).setVisibility(view.INVISIBLE);
                    adapter = new AdapterCategory(view.getContext(), catList);
                    adapter.notifyDataSetChanged();
                    catRecycler.setAdapter(adapter);

                }
                else{
                    Log.i("datatidak ada", "tidak ada");
                    Toast.makeText(view.getContext(), "Data Not Found",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Database Error",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
//        }
//    };
}