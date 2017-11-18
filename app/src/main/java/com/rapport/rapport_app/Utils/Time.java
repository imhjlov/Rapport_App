package com.rapport.rapport_app.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hyunjung on 2017-06-01.
 */

public class Time {

    private String formatTime="-";
    private String formatDate="-";
    private long now;
    private Date date;

    private int hour;
    private int min;
    private int day;

    public void setTime(){
        now=System.currentTimeMillis();//현재시간을 mse으로 구한다
        date = new Date(now);//현재시간을 date변수에 저장
        SimpleDateFormat sdfNow= new SimpleDateFormat("HH:mm");//시간을 나타낼 포맨을 정함
        SimpleDateFormat sdfNowDate=new SimpleDateFormat("yyyy.MM.dd");//날짜를 나타낼 포맷

        setHour(date.getHours());
        setMin(date.getMinutes());
        setDay(date.getDate());

        setFormatTime(sdfNow.format(date));//값을 저장
        setFormatDate(sdfNowDate.format(date));//값을 저장
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


}
