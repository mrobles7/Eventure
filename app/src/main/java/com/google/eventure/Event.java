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

    int id, year, month, day, hour, minute, Ehour, Eminute;
    String EventDate;
    Date FormattedDate;
    String location;
    String description;
    String name;
    Date NotifDate;
    String notifTime;
    String Start;
    String End;


    public Event(){

    }
    public Event (int id, String year, String month, String day, String hour, String minute, String Ehour, String Eminute,
                  String Description, String Name, String Location)
    {
        this.id = id;
        this.year = Integer.parseInt(year);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(day);
        this.hour = Integer.parseInt(hour);
        this.minute = Integer.parseInt(minute);
        String UFDate = year+"/"+month+"/"+day+"/"+hour+"/"+minute;
        this.FormattedDate = convertStringtoDate(UFDate);
        this.Ehour = Integer.parseInt(Ehour);
        this.Eminute = Integer.parseInt(Eminute);
        String notifDate = year+"/"+month+"/"+day+"/"+Ehour+"/"+Eminute;
        this.NotifDate = convertStringtoDate(notifDate);
        this.description = Description;
        this.name = Name;
        this.location = Location;
    }
    public Event(String Name, String location, String description, String Date, String Start, String End) {
        this.name=Name;
        this.location = location;
        this.description = description;
        this.EventDate = Date;
        this.FormattedDate = convertStringtoDate(Date);
        this.Start=Start;
        this.End=End;
    }
    public Event(int id, String Name, String location, String description, String Date, String notifTime,String Start, String End){
        this.id =id;
        this.name=Name;
        this.location = location;
        this.description = description;
        this.EventDate =Date;
        this.FormattedDate = convertStringtoDate(Date);
        this.notifTime = notifTime;
        this.Start = Start;
        this.End=End;
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
            System.out.println("System failed to parse the date.\nError Code: \n" + EX);

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
        this.EventDate=Date;
        this.FormattedDate = convertStringtoDate(Date);
    }
    public void setStart(String Start){
        this.Start=Start;
    }
    public void setEnd(String End){
        this.End=End;
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
    public String getStart(){
        return this.Start;
    }
    public String getEnd(){
        return this.End;
    }
    public String getDescription(){
        return this.description;
    }

    public String getDate(){
        return this.EventDate;
    }
}
