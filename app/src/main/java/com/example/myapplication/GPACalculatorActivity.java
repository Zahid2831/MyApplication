package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GPACalculatorActivity extends AppCompatActivity {

    public void calculateGpa(View v){
        String s1 = editTextQtyPoints.getText().toString();
        String s2 = editTextCreditHours.getText().toString();

        double qtyPoints = Double.parseDouble(s1);
        int creditHours = Integer.parseInt(s2);
        double gpa = qtyPoints/creditHours;

        textViewGpa.setText(gpa+"");
    }
    EditText editTextQtyPoints, editTextCreditHours;
    TextView textViewGpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);


        editTextCreditHours = findViewById(R.id.et_credit_hours);
        editTextQtyPoints = findViewById(R.id.et_qty_points);
        textViewGpa = findViewById(R.id.tv_gpa);
    }




}