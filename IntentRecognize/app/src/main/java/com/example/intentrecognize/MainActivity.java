package com.example.intentrecognize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1; // Код работы
    private ImageButton btnSpeak;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // По стандарту выводим наш layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем кнопку ввода речи и вывода соответствующего текста.
        btnSpeak = (ImageButton) findViewById(R.id.question);
        textView = (TextView) findViewById(R.id.text);

        // Обработчик нажатия на кнопку
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Связываем наше приложение с приложением имеющим нужный Intent filter
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Передаем данные для голоса.
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        "en-US");
                try {
                    // Получаем рез-тат распознования
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "текст не распознан",
                            Toast.LENGTH_SHORT).show();
                }

            }
        };
        btnSpeak.setOnClickListener(listener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(text.get(0));
                }
                break;
            }
        }
    }
}