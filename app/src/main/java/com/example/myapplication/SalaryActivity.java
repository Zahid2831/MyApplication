package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySalaryBinding;

public class SalaryActivity extends AppCompatActivity {

    ActivitySalaryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySalaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void click(View v){
        EditText etSal = binding.inputLayoutSalary.getEditText();
        EditText etBonus = binding.inputLayoutBonus.getEditText();
        if(v.getId() == R.id.btncalculate) {
            String s = etSal.getText().toString();
            String s1 = etBonus.getText().toString();
            float salary = Float.parseFloat(s);
            float bonus = Float.parseFloat(s1);
            float tSal = salary + bonus;
            Toast.makeText(this, tSal + "", Toast.LENGTH_SHORT).show();
        }else{
            etBonus.setText("");
            etSal.setText("");
        }
    }



}