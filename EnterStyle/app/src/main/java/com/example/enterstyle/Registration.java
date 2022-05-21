package com.example.enterstyle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration extends Activity {

    private static final String LOG_TAG = "Registation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Creating view..");
        setContentView(R.layout.registration_layout);
        Log.d(LOG_TAG, "Created view!");
    }

    public void onReg(View view) {

        // Доступ к разметке
        TextView hintR = (TextView) findViewById(R.id.hintReg);

        EditText newLogin = (EditText) findViewById(R.id.editLogin);
        EditText newPassword = (EditText) findViewById(R.id.editPassword);
        EditText newPasswordTry = (EditText) findViewById(R.id.editPasswordTry);

        String l = newLogin.getText().toString();
        String p = newPassword.getText().toString();
        String pT = newPasswordTry.getText().toString();

        // Проверка валидности ввода.
        if (l.length() == 0){
            hintR.setTextColor(Color.RED);
            hintR.setText("Введите логин!");
        } else if (p.length() == 0){
            hintR.setTextColor(Color.RED);
            hintR.setText("Введите пароль!");
        } else if (!p.equals(pT)){
            hintR.setTextColor(Color.RED);
            hintR.setText("Введеные пароли не совпадают!");
        } else {
            String[] newUser = {l, p};
            // Передаем данные основному активити, а этот закрываем.
            Intent i = new Intent();
            i.putExtra("nU", newUser);
            Toast.makeText(getApplicationContext(),
                    "Вы зарегестрированы!",
                    Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, i);
            finish();
        }
    }
}

