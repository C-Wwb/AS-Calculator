package com.example.wwb.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wwb.service.StandardCalculator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_standard_layout);//内容视图
        StandardCalculator standard = new StandardCalculator(this);//标准计算器
    }
}
