package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.WebApi;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.models.User;
import com.example.myapplication.models.UserDetail;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  MainActivity extends AppCompatActivity {



    //declare variables to store address of objects
    //defined in xml file

    ActivityMainBinding binding;
    EditText etId;
    EditText etPassword;
    TextView textViewApiResult;
    private void loginFromApi(){
        WebApi api = RetrofitClient.getInstance().getMyApi();
        UserDetail userDetail = new UserDetail();
        userDetail.userName = etId.getText().toString();
        userDetail.userPassword = etPassword.getText().toString();
        api.logIn(userDetail).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call,
                                   Response<UserDetail> response) {
                if(response.isSuccessful()){
                    UserDetail obj = response.body();
                    if(obj.role.equals("Admin"))
                    {
                        Bundle b = new Bundle();
                        b.putString("ID", obj.userName);
                        b.putString("PWD",obj.userPassword);
                        Intent i = new Intent(
                                MainActivity.this,
                                DashboardActivity.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {

            }
        });
    }


    private void callAddMethod(){
        WebApi api = RetrofitClient.getInstance().getMyApi();
        api.sum(60,34).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call,
                                   Response<Integer> response) {
                if(response.isSuccessful()){
                    Integer result = response.body();
                    Toast.makeText(MainActivity.this,
                            "response : "+ result,
                            Toast.LENGTH_LONG).show();
                    textViewApiResult.setText("response : "+ result);

                }else{

                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(MainActivity.this,
                                "Error response : " + error,
                                Toast.LENGTH_LONG).show();
                      textViewApiResult.setText("Error response : " + error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        t.toString(),
                        Toast.LENGTH_LONG).show();
                textViewApiResult.setText(t.toString());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        textViewApiResult = findViewById(R.id.text_view_api_result);
        callAddMethod();


        etId = findViewById(R.id.et_id);
        etPassword = findViewById(R.id.et_password);
    }//end on onCreate
    public void logIn(View v){

        String id = etId.getText().toString();
        String pwd = etPassword.getText().toString();
        loginFromApi();
        //navigation
//        DBManager mgr = new DBManager(this);
//       // if(id.equals("101") && pwd.equals("abc")){
//        if(mgr.logIn(id, pwd)){
//            Bundle b = new Bundle();
//            b.putString("ID", id);
//            b.putString("PWD",pwd);
//            Intent i = new Intent(
//                    MainActivity.this,
//                    DashboardActivity.class);
//            i.putExtras(b);
//            startActivity(i);
//        }else{
//                    Toast.makeText(this,
//                "Invalid user name or password",
//                Toast.LENGTH_LONG).show();
//        }




//

    }

}