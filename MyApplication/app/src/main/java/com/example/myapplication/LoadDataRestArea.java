package com.example.myapplication;

import java.util.List;

public class LoadDataRestArea {

    List <DataRestArea> list = null;
    String count;
    String numOfRows;
    String pageSize;
    String message;
    String code;

    public LoadDataRestArea(List<DataRestArea> list, String count, String numOfRows, String pageSize, String message, String code) {
        this.list = list;
        this.count = count;
        this.numOfRows = numOfRows;
        this.pageSize = pageSize;
        this.message = message;
        this.code = code;
    }

    public List<DataRestArea> getList() {
        return list;
    }

    public void setList(List<DataRestArea> list) {
        this.list = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
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
