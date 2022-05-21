package com.example.yandexpredictor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String PREDICTOR_URI_JSON = "https://predictor.yandex.net/";
    private static String PREDICTOR_KEY = "pdct.1.1.20160224T140053Z.cb27d8058bc81d29.10b405aa732895274b7d14c9f7a55a116a832d93";
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Сохраняем объекты полей разметки
        editText = (EditText) findViewById(R.id.editText1);
        textView = (TextView) findViewById(R.id.textView1);

        // Обрабатываем нажатия на кнопку
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                getReport();
            }

            // До изменения текста
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            // Во время изменения текста
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("INFO", s.toString());
            }
        });
    }

    void getReport() {

        // Запрос
        Retrofit retrofit = new Retrofit.Builder() // Создаем объект Retrofit
                .baseUrl(PREDICTOR_URI_JSON) // Передаем URL
                .addConverterFactory(GsonConverterFactory.create()) // Добавляем конвертер
                .build();
        RESTApi service = retrofit.create(RESTApi.class); // Добавляем API

        // Создаем объект вызова
        Call<Model> call = service.predict(PREDICTOR_KEY, editText.getText().toString(), "ru");

        // Делаем асинхронный запрос
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                try {
                    String textWord = response.body().text[0].toString();
//                    Log.d("TEST", Arrays.stream(response.body().text)
//                            .map(String::valueOf)
//                            .collect(Collectors.joining(", ")));
//                    Log.d("TEST", response.body().toString());
                    textView.setText("Предиктор: " + textWord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("FAIL", t.getMessage());
                Log.e("FAIL", call.request().toString());
                Log.e("FAIL", "ERROR GET MODEL");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}