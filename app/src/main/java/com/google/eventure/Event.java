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
    String location, description, name, SDate, EDate, NDate;


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
        this.name = Name;
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
        this.name=name;
    }
    public void setlocation(String location){
        this.location=location;
    }
    public void setdescription(String description){
        this.description=description;
    }
    public void setDate(String Date){
        this.SDate=Date;
        this.StartDate = convertStringtoDate(Date);
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getlocation(){
        return this.location;
    }

    public String getDescription(){
        return this.description;
    }

    public String getDate(){
        return this.SDate;
    }
}
