package com.example.quest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import classes.Cross;
import classes.Person;

public class MainActivity extends AppCompatActivity {

    // Создаем нашего бегуна и саму дистанцию.
    public Person myHero;
    public Cross myRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Начало игры
    public void playQuest(View view){

        // Инициализируем игрока
        String name = findViewById(R.id.personName).toString();
        myHero = new Person(name);

        myRun = new Cross(myHero);
    }
}