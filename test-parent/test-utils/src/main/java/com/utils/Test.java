package com.utils;

import java.io.Serializable;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/9 0009 11:10
 */
public class Test implements Serializable{

    private String name = "你好！分模块调用";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
