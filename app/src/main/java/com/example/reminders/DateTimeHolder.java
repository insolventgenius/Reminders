package com.example.reminders;

public class DateTimeHolder {
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public DateTimeHolder(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public DateTimeHolder() {
        this.year = 2020;
        this.month = 04;
        this.day = 04;
        this.hour = 14;
        this.minute = 0;
    }

    public DateTimeHolder(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateTimeHolder(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
