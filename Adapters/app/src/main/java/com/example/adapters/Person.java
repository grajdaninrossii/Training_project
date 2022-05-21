package com.example.adapters;

public class Person {
    private int flag;
    private String name;
    private String money;

    Person(int flag, String name, String money){
        this.flag = flag;
        this.name = name;
        this.money = money;
    }


    public void setFlag(int flag){
        this.flag = flag;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMoney(String money){
        this.money = money;
    }

    public int getFlag(){
        return this.flag;
    }

    public String getName(){
        return this.name;
    }

    public String getMoney(){
        return this.money;
    }
}
