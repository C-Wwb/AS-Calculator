package com.example.wwb.service;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wwb.Utils.Calculate;
import com.example.wwb.calculator.R;
import com.example.wwb.calculator.ScientificCalculatorActivity;
import com.example.wwb.common.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有与基础面板有关的按钮定义
 */
public class StandardCalculator {
    /**
     * 面板
     */
    protected Activity activity; //活动
    protected TextView showInputTv; //显示输入的面板
    protected TextView showResultTv; //显示结果的面板
    /**
     * 数字按钮
     */
    protected Button zeroBtn; //0
    protected Button oneBtn; //1
    protected Button twoBtn; //2
    protected Button threeBtn; //3
    protected Button fourBtn; //4
    protected Button fiveBtn; //5
    protected Button sixBtn; //6
    protected Button sevenBtn; //7
    protected Button eightBtn; //8
    protected Button nineBtn; //9
    /**
     * 操作按钮
     */
    protected Button cBtn; //清空按钮
    protected Button delBtn; //退格按钮
    protected Button resBtn; //保留最近结果
    protected Button divBtn; //除号
    protected Button mulBtn; //乘法
    protected Button subBtn; //减法
    protected Button addBtn; //加法
    protected Button transformBtn; //标准计算器与科学计算器的转换
    protected Button pointBtn; //小数点
    protected Button equBtn; //等于号
    /**
     * 记录
     */
    protected Map<View, String> map; //将View和String映射起来
    protected List<Input> inputList; //定义记录每次输入的数
    protected String lastResult; //记录最后一次的结果，方便继续计算
    protected CurrentStatus currentStatus = CurrentStatus.INIT; //状态标记

    public enum CurrentStatus {
        INIT, CALCU, END, ERROR
    }

    public StandardCalculator() {
    }

    public StandardCalculator(Activity activity) {
        this.activity = activity;
        init();
        setOnClickListener(); //监听器
    }

    /**
     * 初始化 找到对应的按钮
     */
    protected void init() {
        showResultTv = (TextView) activity.findViewById(R.id.show_result_tv);
        showInputTv = (TextView) activity.findViewById(R.id.show_input_tv);
        cBtn = (Button) activity.findViewById(R.id.c_btn); //清空
        delBtn = (Button) activity.findViewById(R.id.del_btn); //退格
        resBtn = (Button) activity.findViewById(R.id.res_btn); //记录最近一次结果
        divBtn = (Button) activity.findViewById(R.id.divide_btn); //除法
        sevenBtn = (Button) activity.findViewById(R.id.seven_btn); //7
        eightBtn = (Button) activity.findViewById(R.id.eight_btn); //8
        nineBtn = (Button) activity.findViewById(R.id.nine_btn); //9
        mulBtn = (Button) activity.findViewById(R.id.multiply_btn); //乘法
        fourBtn = (Button) activity.findViewById(R.id.four_btn); //4
        fiveBtn = (Button) activity.findViewById(R.id.five_btn); //5
        sixBtn = (Button) activity.findViewById(R.id.six_btn); //6
        subBtn = (Button) activity.findViewById(R.id.sub_btn); //减法
        oneBtn = (Button) activity.findViewById(R.id.one_btn); //1
        twoBtn = (Button) activity.findViewById(R.id.two_btn); //2
        threeBtn = (Button) activity.findViewById(R.id.three_btn); //3
        addBtn = (Button) activity.findViewById(R.id.add_btn); //加法
        transformBtn = (Button) activity.findViewById(R.id.transform_btn); //标准计算器转换为科学计算器
        zeroBtn = (Button) activity.findViewById(R.id.zero_btn); //0
        pointBtn = (Button) activity.findViewById(R.id.point_btn); //小数点
        equBtn = (Button) activity.findViewById(R.id.equal_btn); //等于号

        /**
         * map 将视图和字符串映射起来
         */
        map = new HashMap<>();
        map.put(zeroBtn, "0");
        map.put(oneBtn, "1");
        map.put(twoBtn, "2");
        map.put(threeBtn, "3");
        map.put(fourBtn, "4");
        map.put(fiveBtn, "5");
        map.put(sixBtn, "6");
        map.put(sevenBtn, "7");
        map.put(eightBtn, "8");
        map.put(nineBtn, "9");
        map.put(pointBtn, activity.getString(R.string.point));
        map.put(addBtn, "+");
        map.put(subBtn, "-");
        map.put(mulBtn, activity.getString(R.string.multiply) );
        map.put(divBtn, activity.getString(R.string.divide));
        map.put(equBtn, "=");
        inputList = new ArrayList<>();
        lastResult = "";
        initInput();
    }

    protected void setOnClickListener() {
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLastRes();
            }
        });
        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputOpe(v);
            }
        });
        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputOpe(v);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputOpe(v);
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputOpe(v);
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum(v);
            }
        });
        transformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transform();
            }
        });
        equBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult(v);
            }
        });
    }

    protected void transform() {
        Intent intent = new Intent(activity, ScientificCalculatorActivity.class);
        activity.startActivity(intent);
    }


    protected void clear() {
        if (!isInitInputList()) {
            initInput();
            Log.i(activity.getClass().getSimpleName(),"cleaned inputTV \n inputTV: "+showInputTv.getText()
                   +"\n inputList:"+getInputListValues());
        } else {
            showResultTv.setText("");
            Log.i(activity.getClass().getSimpleName(),"cleaned resultTV");
        }
    }

    protected void delete() {
        if (!isInitInputList()) {
            if (inputList.size() == 1) {
                initInput();
            } else {
                Log.i(activity.getClass().getSimpleName(),"delete input: "+ subInputListAndInputTV().getValue());
            }
        }
    }


    protected boolean isInitInputList() {         //判断是否是初始状态
        if(inputList.size()==1 && inputList.get(0).getValue().equals(map.get(zeroBtn))){
            return true;
        }
        return false;
    }

    protected void initInput() {
        inputList.clear();
        inputList.add(new Input(map.get(zeroBtn), Input.TYPE.NUM));
        showInputTv.setText(map.get(zeroBtn));
        currentStatus = CurrentStatus.INIT;
    }


    protected void addTV(View view) {
        showInputTv.setText(showInputTv.getText() + map.get(view));
    }

    protected String updateInputTV(){
        String inputString = "";
        for(Input input : inputList){
            inputString += input.getValue();
        }
        showInputTv.setText(inputString);
        return inputString;
    }

    protected Input subInputListAndInputTV(){
        if(inputList.size()>0){
            Input input =  inputList.remove(inputList.size()-1);
            updateInputTV();
            return input;
        }
        return null;
    }

    protected Input getLastInputItem(){
        if(inputList.size()>0){
            return inputList.get(inputList.size()-1);
        }
        return null;
    }

    protected void inputLastRes(){
        if(!lastResult.isEmpty() && (getLastInputItem().getType()!= Input.TYPE.NUM
                || currentStatus==CurrentStatus.INIT)){
            for(int i = 0;i<lastResult.length();i++){
                inputNum(String.valueOf(lastResult.charAt(i)));
            }
            updateInputTV();
        }
    }


    protected void inputOpe(View view) {
        if(getLastInputItem().getType()== Input.TYPE.OPE){    //连续输入两个OPR型操作符，删除第一个
            Log.i(activity.getClass().getSimpleName(),"delete OPT: "+  subInputListAndInputTV().getValue());
        }
        inputList.add(new Input(map.get(view), Input.TYPE.OPE));
        addTV(view);
        Log.i(activity.getClass().getSimpleName(),"Add OPT; "+map.get(view)+
                "\ninputList:  "+getInputListValues());
    }

    protected void inputNum(View view){
        inputNum(map.get(view));
    }

    protected void inputNum(String num) {
        initEndStatus();           //处理上次运算结束的情况
        if(getLastInputItem().getType()== Input.TYPE.OPE_NUM){
            inputOpe(mulBtn);
        }
        if (isInitInputList() && (!num.equals("."))) {      //初始状态下输入数字替换原始0，小数点不替换
            Log.i(activity.getClass().getSimpleName(),"delete inputTV:  "+subInputListAndInputTV().getValue());
        }
        if(num.equals(".")){       //限制一个数中最多只有一个小数点
            for (int i = inputList.size() - 1; i > 0; i--) {
                if (inputList.get(i).getType() != Input.TYPE.NUM) {
                    break;
                } else if (inputList.get(i).getValue() == map.get(pointBtn)) {
                    return;
                }
            }
        }
        inputList.add(new Input(num, Input.TYPE.NUM));
        showInputTv.setText(showInputTv.getText() + num);
        Log.i(activity.getClass().getSimpleName(), "add:  " + num+
                "\ninputList:  " + getInputListValues());
    }

    protected String getInputListValues(){   //给日志打印inputList信息
        String s = "";
        for(Input input : inputList){
            s += input.getValue()+" ";
        }
        return s;
    }

    protected void initEndStatus(){    //上次计算结束后，初始化input
        if(currentStatus == CurrentStatus.END && getLastInputItem().getType()== Input.TYPE.NUM){
                showResultTv.setText(String.valueOf(showResultTv.getText())+showInputTv.getText());
                initInput();
                currentStatus = CurrentStatus.CALCU;
        }
    }

    protected void getResult(View view) {
        if (showInputTv.getText().charAt(showInputTv.length() - 1) != '=') {
            addTV(view);
        }
        String res = "";
        try{
            res = Calculate.getResult(inputList,activity);    //取得计算结果
            resultManage(res);       //处理界面显示信息
        }catch (ArithmeticException e){
            res = "0不能做除数！";
            Toast.makeText(activity,res,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            res = "算术式不正确！";
            Toast.makeText(activity,res,Toast.LENGTH_SHORT).show();
        }
        Log.i(activity.getClass().getSimpleName(),"got result: "+res+
        "\ninputList:"+getInputListValues());
    }

    protected void resultManage(String result){
        showResultTv.setText(showInputTv.getText());
        showInputTv.setText(result);
        inputList.clear();
        lastResult = result;
        for(char c : result.toCharArray()){
            inputList.add(new Input(String.valueOf(c), Input.TYPE.NUM));
        }
        currentStatus = CurrentStatus.END;
    }
}
