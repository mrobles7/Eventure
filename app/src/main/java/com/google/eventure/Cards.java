package com.google.eventure;

/**
 * Created by MarylouRobles on 11/25/2017.
 */

public class Cards
{
    private int month;
    private int day;
    private int year;
    private int hour;
    private int min;
    private String EventName;
    private String Description;

    public Cards()
    {
        this.EventName = " ";
        this.Description = " ";
    }

    public Cards(String EventName, String Description)
    {
        this.EventName = EventName;
        this.Description = Description;
    }

    public Cards(int month, int day, int year, int hour, int min, String EventName, String Description)
    {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.min =  min;
        this.EventName = EventName;
        this.Description = Description;
    }

    //Setters

    public void setMonth(int month)
    {
        this.month = month;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public void setEventName(String EventName)
    {
        this.EventName = EventName;
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    //Getters

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getYear()
    {
        return year;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMin()
    {
        return min;
    }

    public String getEventName()
    {
        return EventName;
    }

    public String getDescription()
    {
        return Description;
    }

}
