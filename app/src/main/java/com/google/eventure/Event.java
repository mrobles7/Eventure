package com.google.eventure;

import android.icu.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by ReignNelson on 10/8/17.
 */

//Event and classes object
public class Event {

    int id, year, month, day, hour, minute, EHour, EMinute;
    Date StartDate, EndDate, NotifDate;
    String location, description, Ename, SDate, EDate, NDate;
    boolean notification;

    public Event(){

    }
    public Event (int id, int year, int month, int day, int hour, int minute, int EHour, int EMinute, int NHour, int NMinute,
                  String Description, String Name, String Location)
    {
        SDate = year+"/"+month+"/"+day+"/"+hour+"/"+minute;
        EDate = year+"/"+month+"/"+day+"/"+EHour+"/"+EMinute;
        NDate = year+"/"+month+"/"+day+"/"+NHour+"/"+NMinute;
        this.StartDate = convertStringtoDate(SDate);
        this.EndDate = convertStringtoDate(EDate);
        this.NotifDate = convertStringtoDate(NDate);
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.EHour = EHour;
        this.EMinute = EMinute;
        this.description = Description;
        this.Ename = Name;
        this.location = Location;
    }

    //this method was pulled off of
    //https://beginnersbook.com/2013/04/java-string-to-date-conversion/
    //as a way to convert strings to dates
    //all credit goes to the author
    private Date convertStringtoDate (String dateString)
    {
        Date FDate = null;
        DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd/HH/mm");
        try{
            FDate = df.parse(dateString);
        }catch(Exception EX) {
            System.out.println("System failed to parse the date\nError Code: " + EX);

        }
        return FDate;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.Ename=name;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setmonth(int month){
        this.month=month;
    }
    public void setday(int day){
        this.day=day;
    }
    public void setMinute(int minute){
        this.minute=minute;
    }
    public void setlocation(String location){
        this.location=location;
    }
    public void setdescription(String description){
        this.description=description;
    }
    public void sethour(int hour){
        this.hour=hour;
    }
    public void setDate(String Date){
        this.SDate=Date;
        this.StartDate = convertStringtoDate(Date);
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.Ename;
    }
    public String getlocation(){
        return this.location;
    }

    public String getDescription(){
        return this.description;
    }
    public String getStart() {
        String rs= hour + ":" + minute;
        return rs;
    }
    public String getEnd(){
        String rs = EHour + ":" + EMinute;
        return rs;
    }
    public String getDate(){
        return this.SDate;
    }

    public int getyear() {
        return year;
    }

    public int getmonth() {
        return month;
    }

    public int getday() {
        return day;
    }

    public int gethour() {
        return hour;
    }

    public int getminute() {
        return minute;
    }

    public boolean getnotification() {
        return notification;
    }
}
