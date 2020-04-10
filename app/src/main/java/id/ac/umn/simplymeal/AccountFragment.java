package id.ac.umn.simplymeal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AccountFragment extends Fragment {
    private TextView infoUsr;
    private Button logOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);


//        infoUsr = view.findViewById(R.id.display);
//        infoUsr.setText(Preferences.getLoggedInUser(getBaseContext()));
//
//        logOut = view.findViewById(R.id.btnLogout);
//
//        logOut.setOnClickListener(new View.OnClickListener() {

//        @Override
//        public void onClick(View v) {
//            Preferences.clearLoggedInUser(getBaseContext());
//            startActivity(new Intent(getBaseContext(), LoginActivity.class));
//            finish();
//            }
//        });
        return view;
    }
}

