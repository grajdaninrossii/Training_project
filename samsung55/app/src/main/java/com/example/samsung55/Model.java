package com.example.samsung55;

public class Model {

    private int id;
    private String lastname;
    private String name;
    private String classname;

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getLastname(){return this.lastname;}
    public String getClassname(){return this.classname;}

    public void setId(int id){this.id = id; }
    public void setName(String name){this.name = name;}
    public void setLastname(String lastname){this.lastname = lastname;}
    public void setClassname(String classname){this.classname = classname;}
}
