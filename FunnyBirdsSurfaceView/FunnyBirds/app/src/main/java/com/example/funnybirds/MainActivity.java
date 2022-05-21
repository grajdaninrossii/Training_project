package com.example.funnybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GameView myGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main)
        myGame = new GameView(this);
        setContentView(myGame);

        Toast.makeText(getApplicationContext(),
                "Начало игры! Набери как можно больше очков. " +
                        "Не врезайся в птиц! " +
                        "Птицы бомбы можно взрывать нажатием!", Toast.LENGTH_SHORT).show();
    }

    // Java
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        myGame.onPause = true;
    }
}