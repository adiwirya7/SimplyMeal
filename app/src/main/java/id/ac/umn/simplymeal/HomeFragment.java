package id.ac.umn.simplymeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ASIAN CATEGORY
        LinearLayout asian_category = (LinearLayout) view.findViewById(R.id.asiancategory);
        asian_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentasiancategory = new Intent(getActivity(), AsianCategory.class);
                startActivity(intentasiancategory);
            }
        });

        //INDONESIAN CATEGORY
        LinearLayout indonesian_category = (LinearLayout) view.findViewById(R.id.indonesiancategory);
        indonesian_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentindonesiancategory = new Intent(getActivity(), IndonesianCategory.class);
                startActivity(intentindonesiancategory);
            }
        });

        //WESTERN CATEGORY
        LinearLayout western_category = (LinearLayout) view.findViewById(R.id.westerncategory);
        western_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentwesterncategory = new Intent(getActivity(), WesternCategory.class);
                startActivity(intentwesterncategory);
            }
        });

//        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
//        carouselView.setPageCount(sampleImages.length);

//        carouselView.setImageListener(imageListener);
        return view;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}