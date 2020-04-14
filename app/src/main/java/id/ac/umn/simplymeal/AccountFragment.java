package id.ac.umn.simplymeal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import id.ac.umn.simplymeal.loginregister.LoginActivity;
import id.ac.umn.simplymeal.loginregister.Preferences;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    private TextView infoUsr;
    private Button logOut;
    private BottomNavigationView navigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        final Context context = getContext();

        infoUsr = view.findViewById(R.id.display);
        infoUsr.setText(Preferences.getLoggedInUser(context));

        logOut = view.findViewById(R.id.btnLogout);

        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Preferences.clearLoggedInUser(context);
                startActivity(new Intent(context, LoginActivity.class));
                getActivity().finish();
                }
            });
        return view;
    }



}

