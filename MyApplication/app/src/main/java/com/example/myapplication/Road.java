package com.example.myapplication;

import android.app.Application;

import java.io.Serializable;

public class Road implements Serializable {
    public Road(String conzoneName, String routeName, String stdHour, String updownTypeCode) {
        this.conzoneName = conzoneName;
        this.grade = "1";
        this.routeName = routeName;
        this.stdHour = stdHour;
        this.updownTypeCode = updownTypeCode;
    }

    public String getConzoneName() {
        return conzoneName;
    }

    public void setConzoneName(String conzoneName) {
        this.conzoneName = conzoneName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStdHour() {
        return stdHour;
    }

    public void setStdHour(String stdHour) {
        this.stdHour = stdHour;
    }

    public String getUpdownTypeCode() {
        return updownTypeCode;
    }

    public void setUpdownTypeCode(String updownTypeCode) {
        this.updownTypeCode = updownTypeCode;
    }

    @Override
    public String toString(){
        return String.format("구간: %s\n"+"등급:%s"+"노선: %s\n"+ "시간: %s\n"+"방향:%s",conzoneName,grade,routeName,stdHour,updownTypeCode);
    }

    //속한 구간
    String conzoneName;
    //해당 구역 소통 등급
    String grade;
    //속한 고속도로명
    String routeName;
    //갱신 시간
    String stdHour;
    //기점 종점 방향
    String updownTypeCode;

}
