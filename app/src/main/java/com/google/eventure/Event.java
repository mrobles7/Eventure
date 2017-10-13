package com.google.eventure;

import java.sql.Timestamp;

/**
 * Created by ReignNelson on 10/8/17.
 */

public class Event {
    int id;
    String Date;
    String location;
    String description;
    String name;


    public Event() {

    }
    public Event(int id,String Name, String location, String description, String Date){
        this.id =id;
        this.name=Name;
        this.location = location;
        this.description = description;
        this.Date =Date;
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
        this.Date=Date;
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
        return this.Date;
    }
}
