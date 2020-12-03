package com.example.myapplication;

import java.io.Serializable;

public class DataRestArea implements Serializable {

    String unitName;
    String routeName;
    String xValue;
    String yValue;
    String numOfRows;
    String pageNo;

    public DataRestArea(String unitName, String routeName, String xValue, String yValue, String numOfRows, String pageNo) {
        this.unitName = unitName;
        this.routeName = routeName;
        this.xValue = xValue;
        this.yValue = yValue;
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getyValue() {
        return yValue;
    }

    public void setyValue(String yValue) {
        this.yValue = yValue;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
