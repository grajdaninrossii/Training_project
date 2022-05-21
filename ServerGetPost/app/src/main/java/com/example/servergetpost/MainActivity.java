package com.example.servergetpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String answerHTTP, answerHTTPPost;

    TextView name;
    Context context;
    String lastnameS, firstnameS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        name = (TextView) findViewById(R.id.result);
    }

    public void sendGET(View v){
        OkHttpGet sg = new OkHttpGet();
        sg.get();
        if (answerHTTP != null){
            name.setText(answerHTTP);
        }
    }

    public void sendPOST(View v){

        EditText lastname = (EditText) findViewById(R.id.lastname);
        EditText firstname = (EditText) findViewById(R.id.firstname);
        lastnameS = lastname.getText().toString();
        firstnameS = firstname.getText().toString();
        OkHttpPost sp = new OkHttpPost();
        sp.initPost();
        if (answerHTTPPost == null){
            Toast.makeText(context, "Данные не отправлены", Toast.LENGTH_LONG).show();
        }
    }


    public class OkHttpGet {

        public void get() {

            //1.okhttpClient объект
            OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    // Здесь также можно установить кеш данных и т. Д.
                    // Установить тайм-аут
                            connectTimeout(15, TimeUnit.SECONDS).
                            readTimeout(20, TimeUnit.SECONDS).
                            writeTimeout(20,  TimeUnit.SECONDS).
                    // Ошибка повторного подключения
                            retryOnConnectionFailure(true).
                            build();

            // 2 Запрос на создание,
            //builder.get () представляет собой запрос на получение, параметр, помещенный в метод url, является сетевым адресом
            Request.Builder builder = new Request.Builder();

            Request request = builder.get().url("http://127.0.0.1:8080").build();

            // 3 Инкапсулируем запрос в вызов
            Call call = okHttpClient.newCall(request);

            // 4, выполнить вызов, этот метод предназначен для асинхронного запроса данных
            call.enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call arg0, IOException arg1) {

                    // Не удалось позвонить
                    TextView answer = (TextView) findViewById(R.id.result);
                    answer.setText( "Не удалось");
                }

                @Override
                // Поскольку OkHttp полагается на поле Content-Type в заголовке ответа, чтобы определить метод декодирования при синтаксическом анализе ответа
                // OkHttp будет использовать метод кодирования UTF-8 по умолчанию для декодирования
                // Здесь используется асинхронная загрузка, если нужно использовать элемент управления, вызовите его в основном потоке
                public void onResponse(Call arg0, Response arg1) throws IOException {

                    // Успешный вызов
                    answerHTTP = arg1.body().string();
                }
            });

        }
    }

    public class OkHttpPost {

        public void initPost() {

            //1.okhttpClient объект
            OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    // Здесь также можно настроить кеширование данных и т. Д.
                    // Установить тайм-аут
                            connectTimeout(15, TimeUnit.SECONDS).
                            readTimeout(20, TimeUnit.SECONDS).
                            writeTimeout(20,  TimeUnit.SECONDS).
                    // Ошибка повторного подключения
                            retryOnConnectionFailure(true).
                            build();

            RequestBody requestBodyPost = new FormBody.Builder()
                    .add("firstname", firstnameS)
                    .add("lastname", lastnameS)
                    .build();

            Request requestPost = new Request.Builder()
                    .url("http://127.0.0.1:8080")
                    .post(requestBodyPost)
                    .build();

            okHttpClient.newCall(requestPost).enqueue(new Callback() {

                @Override
                public void onFailure(Call arg0, IOException arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onResponse(Call arg0, Response arg1) throws IOException {

                    // okHttp также поддерживает обработку GJson
                    // Список <beans> и обработка bean-компонента могут быть выполнены здесь
//                    answerHTTPPost

                }
            });
        }
    }
}

class User {
    public String firstname;
    public String lastname;
}