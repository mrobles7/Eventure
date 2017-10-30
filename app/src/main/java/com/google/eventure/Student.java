package com.google.eventure;

/**
 * Created by ReignNelson on 10/2/17.
 */

public class Student {
    private int id;
    private String FirstName;
    private String Username;
    private String Password;
    private String Email;

    public Student(){

    }

    public Student(String Usermame,String FirstName,String Password,String Email) {
        this.Username =  Usermame;
        this.FirstName = FirstName;
        this.Password = Password;
        this.Email = Email;
    }
    public Student(int id, String Usermame,String FirstName,String Password,String Email) {
        this.id = id;
        this.Username =  Usermame;
        this.FirstName = FirstName;
        this.Password = Password;
        this.Email = Email;
    }
    public void setID(int id){
        this.id=id;
    }

    public void setName(String name){
        this.FirstName=name;
    }

    public void setUsermame(String username) {
        this.Username= username;
    }
    public void setEmail(String Email) {
        this.Email= Email;
    }
    public void setPassword(String password) {
       this.Password =password;
    }
    public int getID() {
        return id;
    }
    public String getName() {
        return FirstName;
    }
    public String getEmail() {
        return Email;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
}
