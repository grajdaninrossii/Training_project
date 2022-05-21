package classes;

public class Person {

    String name;
    int health;


    public Person(String newName, int newHealth){
        name = newName;
        health = newHealth;
    }

    public Person(String newName){
        this(newName, 100);
    }
}
