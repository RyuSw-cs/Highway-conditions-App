package com.example.myapplication;

import java.io.Serializable;

public class DataHighway implements Serializable {
    public DataHighway(String stdHour, String routeNo, String routeName, String updownTypeCode, String vdsId, String trafficAmout, String shareRatio, String conzoneId, String conzoneName, String stdDate, String speed, String timeAvg, String grade) {
        this.stdHour = stdHour;
        this.routeNo = routeNo;
        this.routeName = routeName;
        this.updownTypeCode = updownTypeCode;
        this.vdsId = vdsId;
        this.trafficAmout = trafficAmout;
        this.shareRatio = shareRatio;
        this.conzoneId = conzoneId;
        this.conzoneName = conzoneName;
        this.stdDate = stdDate;
        this.speed = speed;
        this.timeAvg = timeAvg;
        this.grade = grade;
    }

    public String getStdHour() {
        return stdHour;
    }

    public void setStdHour(String stdHour) {
        this.stdHour = stdHour;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getUpdownTypeCode() {
        return updownTypeCode;
    }

    public void setUpdownTypeCode(String updownTypeCode) {
        this.updownTypeCode = updownTypeCode;
    }

    public String getVdsId() {
        return vdsId;
    }

    public void setVdsId(String vdsId) {
        this.vdsId = vdsId;
    }

    public String getTrafficAmout() {
        return trafficAmout;
    }

    public void setTrafficAmout(String trafficAmout) {
        this.trafficAmout = trafficAmout;
    }

    public String getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(String shareRatio) {
        this.shareRatio = shareRatio;
    }

    public String getConzoneId() {
        return conzoneId;
    }

    public void setConzoneId(String conzoneId) {
        this.conzoneId = conzoneId;
    }

    public String getConzoneName() {
        return conzoneName;
    }

    public void setConzoneName(String conzoneName) {
        this.conzoneName = conzoneName;
    }

    public String getStdDate() {
        return stdDate;
    }

    public void setStdDate(String stdDate) {
        this.stdDate = stdDate;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTimeAvg() {
        return timeAvg;
    }

    public void setTimeAvg(String timeAvg) {
        this.timeAvg = timeAvg;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String stdHour;
    private String routeNo;
    private String routeName;
    private String updownTypeCode;
    private String vdsId;
    private String trafficAmout;
    private String shareRatio;
    private String conzoneId;
    private String conzoneName;
    private String stdDate;
    private String speed;
    private String timeAvg;
    private String grade;
}
