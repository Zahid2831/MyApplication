package com.example.myapplication.fragements;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.WebApi;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.example.myapplication.db.DBManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    final int PERMISSION_REQUEST_CODE = 1001;
    final int PERMISSION_REQUEST_GALLERY_CODE = 1002;
    final int CAMERA = 1019;
    final int GALLERY = 1020;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentProfileBinding.inflate(getLayoutInflater());

      binding.imageViewProfile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              requestReadWritePermission();
              //requestPermission();
          }
      });

      binding.buttonSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              String department =
                      binding.spinnerDepartment.
                              getSelectedItem().toString();

              if(binding.radioButtonMarried.isChecked()){
                  Toast.makeText(getActivity(),
                          "Married", Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(getActivity(),
                          "UnMarried", Toast.LENGTH_SHORT).show();
              }


          }
      });
      populateDepartmentSpinner();
      return binding.getRoot();
    }
    private void populateDepartmentSpinner(){
        WebApi api = RetrofitClient.getInstance().getMyApi();
        api.getList().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call,
                                   Response<ArrayList<String>> response) {
                if(response.isSuccessful()){
                    ArrayList<String> data = new ArrayList<>();
                    data = response.body();
//        data.add("HR");
//        data.add("Marketing");
//        data.add("Accounts");
//                    DBManager mgr = new DBManager(getActivity());
//                    data = mgr.getAllDepartments();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            getActivity(),
                            android.R.layout.simple_spinner_dropdown_item,
                            data
                    );
                    binding.spinnerDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(),
                        "Granted",
                        Toast.LENGTH_SHORT).show();
                openCamera();
            }else{
                Toast.makeText(getActivity(),
                        "Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void requestReadWritePermission(){
        if(checkReadWritePermissions() == false){
            //if permission not granted
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    PERMISSION_REQUEST_GALLERY_CODE);

        }else{
            //permission already granted
            openGallery();
        }
    }

    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,GALLERY);
    }

    public void requestPermission(){
        if(checkPermissions() == false){
            //if permission not granted
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);

        }else{
            //permission already granted
            openCamera();
        }
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA
                && resultCode == Activity.RESULT_OK){
            Bundle b = data.getExtras();
            Bitmap bmp = (Bitmap) b.get("data");
            binding.imageViewProfile.setImageBitmap(bmp);
        }else if(requestCode == GALLERY
                && resultCode == Activity.RESULT_OK){
            Uri imgUri = data.getData();
            binding.imageViewProfile.setImageURI(imgUri);
        }
    }

    public void openCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,CAMERA);
    }
    public boolean checkReadWritePermissions(){
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkPermissions(){
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            return false;
        }
    }







}