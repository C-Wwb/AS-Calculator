package com.example.wwb.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wwb.service.ScientificCalculator;

public class ScientificCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_scientific_layout);//内容视图
        ScientificCalculator scientificCalculator = new ScientificCalculator(this);//科学计算器
    }
}
