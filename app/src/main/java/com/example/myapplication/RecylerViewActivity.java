package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.myapplication.adapters.UserAdapter;
import com.example.myapplication.databinding.ActivityRecylerViewBinding;
import com.example.myapplication.models.UserDetail;

import java.util.ArrayList;

public class RecylerViewActivity extends AppCompatActivity {

    ActivityRecylerViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecylerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        populateRecyclerView();
    }
    private void populateRecyclerView(){
        ArrayList<UserDetail> details = new ArrayList<>();
        UserDetail d1 = new UserDetail();d1.userName = "Ali";
        d1.userPassword = "abc";
        UserDetail d2 = new UserDetail();d2.userName = "Basit";
        d2.userPassword = "1";
        details.add(d1);
        details.add(d2);
        UserAdapter adapter = new UserAdapter(this, details);
        binding.recyclerViewUsers.setAdapter(adapter);

    }

    public void deleteUser(UserDetail obj) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Confirmation!!!");
        //builder.setMessage("Do you really want to delete user? ");

        builder.setItems(new String[]{"Open Camera", "Choose from gallery"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){

                        }else{

                        }
                    }
                });

//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}