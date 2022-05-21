package com.example.gson_53;

public class User {

    private String firstname;
    private String lastname;
    private int school;


    User(String firstname, String lastname, int school){
        this.firstname = firstname;
        this.lastname = lastname;
        this.school = school;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    @Override
    public  String toString(){
        return "firstname: " + firstname + " lastname: " + lastname + " school: " + school;
    }
}