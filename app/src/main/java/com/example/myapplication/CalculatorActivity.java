package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity
        implements View.OnClickListener {

    ActivityCalculatorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonSum.setOnClickListener(this);
        binding.buttonSubtract.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_sum){

        }else if(v.getId() == R.id.button_subtract){

        }
        Toast.makeText(this, "event occurred",
                Toast.LENGTH_SHORT).show();
    }
}