package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.WebApi;
import com.example.myapplication.databinding.ActivitySignUpBinding;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.models.UserDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String uName = binding.editTextUserName.getText().toString();
                    String uPassword = binding.editTextPassword.getText().toString();
                    //DBManager mgr = new DBManager(SignUpActivity.this);
                    //mgr.saveUser(uName, uPassword);
                    UserDetail userObj = new UserDetail();
                    userObj.userName = uName;
                    userObj.userPassword = uPassword;
                    userObj.role = "Admin";
                    WebApi api = RetrofitClient.getInstance().getMyApi();
                    api.signup(userObj).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call,
                                               Response<String> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,
                                        "Data Saved!",
                                        Toast.LENGTH_SHORT).show();
                            }else{

                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }catch (Exception e){
                    Toast.makeText(SignUpActivity.this,
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}