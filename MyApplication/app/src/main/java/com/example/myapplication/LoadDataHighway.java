package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoadDataHighway {

    private List<DataHighway> list = null;
    private int count;
    private String message;
    private String code;

    public LoadDataHighway(List<DataHighway> list, int count, String message, String code) {
        this.list = list;
        this.count = count;
        this.message = message;
        this.code = code;
    }

    public List<DataHighway> getDhh() {
        return list;
    }

    public void setDhh(List<DataHighway> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
