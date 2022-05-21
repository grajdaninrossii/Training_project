package com.example.enterstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String[]> myUsers = new ArrayList<String[]>();

    private static final String LOG_TAG = "Main_Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Creating view..");
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Created view!");
    }

    public void onEntry(View view){
        EditText l = (EditText)findViewById(R.id.login);
        EditText p = (EditText)findViewById(R.id.editTextTextPassword);

        String login = l.getText().toString();
        String password = p.getText().toString();
        TextView result = (TextView) findViewById(R.id.textView);
//        result.setText("Все хорошо!");
//        Log.d("onPress", String.valueOf(login == myLogin));

        // Проверка на вход.
        if (myUsers.size() != 0) {
            boolean check = true;
            for (String[] mass: myUsers)
                if (mass[0].equals(login) && mass[1].equals(password)) {
                    result.setTextColor(Color.GREEN);
                    result.setText("Все хорошо!");
                    check = false;
                    break;
                }
            if (check){
                result.setTextColor(Color.RED);
                result.setText("Неправильный логин или пароль!");
                l.setText("");
                p.setText("");
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Еще нет зарегестрированных пользователей!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Кнопка регистрации
    public void onRegistry(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        try {
            // Получаем рез-тат распознования
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                "Чет пошло не так",
                Toast.LENGTH_SHORT).show();
        }
    }

    // Принимаем рез-ты от экрана регистрации
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { // Если все прошло без ошибок, принимаем данные и записавем в массив.
            case RESULT_OK:
                myUsers.add(data.getStringArrayExtra("nU"));
                Log.d(LOG_TAG, myUsers.get(0)[0]);
                break;
        }
    }
}