package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.databinding.ActivityBmicalcualtorBinding;

public class BMICalcualtorActivity extends AppCompatActivity {

    ActivityBmicalcualtorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBmicalcualtorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText etHeight = binding.inputLayoutHeight.getEditText();
                EditText etWeight = binding.inputLayoutWeight.getEditText();
                String s1 = etHeight.getText().toString();

                String s2 = etWeight.getText().toString();
                double height = Double.parseDouble(s1);
                double weight = Double.parseDouble(s2);
                double bmi = (weight)/(height*height);
                binding.tvBmi.setText(bmi+"");
            }
        });
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });











    }
}