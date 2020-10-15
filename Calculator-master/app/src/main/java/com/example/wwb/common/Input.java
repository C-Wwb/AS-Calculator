package com.example.wwb.common;

import java.util.List;

/**
 * 定义input类，规范所有的输入
 */
public class Input {
    private String value;
    private TYPE type;
    private List<TYPE> frontType;
    public static enum TYPE{
        NUM,        //一般所用的元素，包括'.'and'0..9'
        OPE,         //需要进行操作的 加减乘除 乘方
        OPE_NUM,   //特殊的数字 PI,e
        NUM_OPE,   //三角函数 根 etc sin,lg,ln,root,
        OPE_OPE,    //倒数，阶乘，,%
        LEFT_BRACKET,//左括号
        RIGHT_BRACKET,//右括号
    }

    public Input(){}

    /**
     *
     * @param value 输入的值
     * @param type 输入的类型
     */
    public Input(String value, TYPE type){
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
