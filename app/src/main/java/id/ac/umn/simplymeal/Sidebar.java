//package id.ac.umn.simplymeal;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class Sidebar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    Toolbar toolbar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sidebar);
////        toolbar = findViewById(R.id.MainToolbar);
////        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                drawerLayout,
//                toolbar,
//                R.string.openNavDrawer,
//                R.string.closeNavDrawer
//        );
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//}
//
