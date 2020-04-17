package id.ac.umn.simplymeal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import id.ac.umn.simplymeal.loginregister.LoginActivity;
import id.ac.umn.simplymeal.loginregister.Preferences;

import static android.app.Activity.RESULT_OK;
import static com.google.firebase.storage.FirebaseStorage.getInstance;


public class AccountFragment extends Fragment {
    private TextView infoUsr;
    private Button logOut, btn_login;
    private ConstraintLayout toLoginScreen;
    private RelativeLayout profileScreen;
    private BottomNavigationView navigation;
    private ImageView imgProfile;

    FloatingActionButton fab;
    ProgressDialog pd;
    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    DatabaseReference databaseReference;

    //storage
    StorageReference storageReference;
    //Path buat save image
    String storagePath = "Users_Profile_Image/";
    //permission
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    String[] cameraPermissions;
    String storagePermissions[];

    Uri image_uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        final Context context = getContext();
        final String userName = Preferences.getLoggedInUser(context);

        databaseReference =FirebaseDatabase.getInstance().getReference().child("Users");
        storageReference = getInstance().getReference();

        //Get info according to currently signed user
        if(firebaseAuth.getCurrentUser() != null && (Preferences.getLoggedInStatus(context) == true)) {
            view = inflater.inflate(R.layout.fragment_account, container, false);

            imgProfile = view.findViewById(R.id.myPict);
            final TextView name = view.findViewById(R.id.name);
            final TextView birthOfDate = view.findViewById(R.id.birth);
            final TextView address = view.findViewById(R.id.address);
            final TextView gender = view.findViewById(R.id.gender);
            final TextView phoneNumber = view.findViewById(R.id.noHp);
            final TextView email = view.findViewById(R.id.email);
//            fab = view.findViewById(R.id.fab);
            pd = new ProgressDialog(getActivity());
            logOut = view.findViewById(R.id.btnLogout);

            cameraPermissions = new String[] {
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };
            storagePermissions = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE };

            databaseReference.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //check until required data
                    if(dataSnapshot.exists()){
                        //Get data
                        String nama = String.valueOf(dataSnapshot.child("firstName").getValue() + " " + dataSnapshot.child(
                                "lastName").getValue());
                        String emails = "" + dataSnapshot.child("emailUser").getValue();
                        String bod = "" + dataSnapshot.child("birthOfDate").getValue();
                        String gen = "" + dataSnapshot.child("gender").getValue();
                        String addres = "" + dataSnapshot.child("address").getValue();
                        String hp = "" + dataSnapshot.child("phoneNumber").getValue();
                        String image = "" + dataSnapshot.child("profilePhoto").getValue();

                        //Set data
                        name.setText(nama);
                        email.setText(emails);
                        gender.setText(gen);
                        birthOfDate.setText(bod);
                        phoneNumber.setText(hp);
                        address.setText(addres);

                        try {
                            //jika ada image
                            Log.e("c", "onDataChange: abcde");
//                            imgProfile.setImageResource(Integer.parseInt(image));
                            Picasso.get().load(image).into(imgProfile);
                        } catch (Exception e) {
                            //jika gaada image -> set default
                            Picasso.get().load(R.drawable.ic_person).into(imgProfile);
                        }
                    }
                    else{
                        Log.i("noData","Gak ada data");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showEditProfileDialog();
//                }
//            });
            Button buttonEditName = view.findViewById(R.id.buttonEditName);

            buttonEditName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editName();
                }
            });

            view.findViewById(R.id.buttonEditAddress).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editAddress();
                }
            });

            view.findViewById(R.id.buttonEditGender).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editGender();
                }
            });

            view.findViewById(R.id.buttonEditBoD).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editBoD();
                }
            });

            logOut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Preferences.clearLoggedInUser(context);
                    startActivity(new Intent(context, LoginActivity.class));
                    getActivity().finish();
                }
            });
        }
        else{
            view = inflater.inflate(R.layout.fragment_noaccount, container, false);

            btn_login = view.findViewById(R.id.btnLogin);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, LoginActivity.class));
                    getActivity().finish();
                }
            });
        }

        return view;
    }

    private void editBoD() {
        Calendar calendar;
        int year, month, dayOfMonth;
        Date date;
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String mon = "";
                        switch (month){
                            case 1:
                                mon = "January";
                                break;
                            case 2:
                                mon = "February";
                                break;
                            case 3:
                                mon = "Maret";
                                break;
                            case 4:
                                mon = "April";
                                break;
                            case 5:
                                mon = "May";
                                break;
                            case 6:
                                mon = "June";
                                break;
                            case 7:
                                mon = "July";
                                break;
                            case 8:
                                mon = "August";
                                break;
                            case 9:
                                mon = "September";
                                break;
                            case 10:
                                mon = "October";
                                break;
                            case 11:
                                mon = "November";
                                break;
                            case 12:
                                mon = "December";
                                break;
                        }
                        Log.d("TAG", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                        String date = day + " " + mon + " " + year;

                        final Context context = getContext();
                        final String userName = Preferences.getLoggedInUser(context);
                        databaseReference.child(userName).child("birthOfDate").setValue(date);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void editGender() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.fragment_edit_gender, null);
        final RadioGroup rgGender = alertLayout.findViewById(R.id.rg_gender);
//        int id = rgGender.getCheckedRadioButtonId();
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Edit Gender");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gen = "";
                switch (rgGender.getCheckedRadioButtonId()){
                    case R.id.rb_male :
                        gen = "Male";
                        break;
                    case R.id.rb_female :
                        gen = "Female";
                        break;
                }

                final Context context = getContext();
                final String userName = Preferences.getLoggedInUser(context);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(userName).child("gender").setValue(gen);

//                rgGender.onEditorAction(EditorInfo.IME_ACTION_DONE);
//                address.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void editName() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.fragment_edit_name, null);
        final EditText fname = alertLayout.findViewById(R.id.fname);
        final EditText lname = alertLayout.findViewById(R.id.lname);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Edit Name");
        alert.setView(alertLayout);
//         disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();

                final Context context = getContext();
                final String userName = Preferences.getLoggedInUser(context);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(userName).child("firstName").setValue(firstName);
                databaseReference.child(userName).child("lastName").setValue(lastName);

                fname.onEditorAction(EditorInfo.IME_ACTION_DONE);
                lname.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void editAddress() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.fragment_edit_address, null);
        final EditText address = alertLayout.findViewById(R.id.address);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Edit Address");
        alert.setView(alertLayout);
//         disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String add = address.getText().toString();

                final Context context = getContext();
                final String userName = Preferences.getLoggedInUser(context);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(userName).child("address").setValue(add);

                address.onEditorAction(EditorInfo.IME_ACTION_DONE);
                address.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_DENIED);
        return result;
    }

    public void requestStoragePermission(){
        requestPermissions(storagePermissions, STORAGE_REQUEST_CODE);
    }

    public boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_DENIED);

        boolean result1 = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_DENIED);
        return (result && result1);
    }

    public void requestCameraPermission(){
        requestPermissions(cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private void showEditProfileDialog(){
        String options[] = {"Edit Profile Picture", "Edit Profile Data"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Action");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    pd.setMessage("Updating Profile Picture");
                    showImagePicDialog();
                }else if(which == 1){
                    pd.setMessage("Updating Profile Data");
                    showDataUpdateDialog("name");
                }
            }
        });

        builder.create().show();
    }

    private void showDataUpdateDialog(final String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Update Data Profile");
        //set layout
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(10, 10, 10, 10);

        //add edit text
        final EditText editText = new EditText(getActivity());
        editText.setHint("Name");
        linearLayout.addView(editText);

        builder.setView(linearLayout);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String value = editText.getText().toString().trim();
                if(!TextUtils.isEmpty(value)){
                    pd.show();
                    HashMap<String, Object> result = new HashMap<>();
                    result.put(key, value);

                    databaseReference.child(user.getUid()).updateChildren(result)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    pd.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    pd.dismiss();
                                    Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //camera
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else{
                        pickFromCamera();
                    }
                }else if(which == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        pickFromGallery();
                    }
                }
            }
        });

        builder.create().show();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean writeStorageAccepted =
//                            grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    Log.e("camera", "onRequestPermissionsResult: " + writeStorageAccepted );
                    if(cameraAccepted && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        pickFromCamera();
                    }else{
                        //permission denied
                        Toast.makeText(getActivity(), "Please enable camera and storage " +
                                "permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                boolean writeStorageAccepted =
                        grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if(writeStorageAccepted){
                    pickFromGallery();
                }else{
                    //permission denied
                    Toast.makeText(getActivity(), "Please enable storage " +
                            "permission", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                image_uri = data.getData();
                uploadProfile(image_uri);
            }
            if(requestCode == IMAGE_PICK_CAMERA_CODE){
                uploadProfile(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadProfile(final Uri uri) {
        String filePathAndName = storagePath + "_" + user.getUid();
        StorageReference storageReference2nd = storageReference.child(filePathAndName);
        storageReference2nd.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //upload image
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isSuccessful()){
                            Uri downloadUri = uriTask.getResult();

                            //check ke upload atau ngga
                            if(uriTask.isSuccessful()) {
                                //Image uploaded
                                HashMap<String, Object> results = new HashMap<>();
                                String image = "image";
                                results.put(image, downloadUri.toString());

                                databaseReference.child(user.getUid()).updateChildren(results)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                pd.dismiss();
                                                Toast.makeText(getActivity(), "Image Updated...",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                pd.dismiss();
                                                Toast.makeText(getActivity(), "Error Updating Image.." +
                                                        ".", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            else{
                                //error
                                pd.dismiss();
                                Toast.makeText(getActivity(), "Some error occured",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void pickFromCamera() {
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, "Temp Pic");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");
//
//        image_uri =
//                getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private void pickFromGallery() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission Needed")
                        .setMessage("This permission is needed because...")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        STORAGE_REQUEST_CODE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_REQUEST_CODE);
            }
        } else {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
        }
    }
}
//imgProfile.setImageResource(R.drawable.profile);