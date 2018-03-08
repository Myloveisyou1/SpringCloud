package com.jm.jmapi.domain;

import java.io.Serializable;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/8 0008 17:26
 */
public class User implements Serializable{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
