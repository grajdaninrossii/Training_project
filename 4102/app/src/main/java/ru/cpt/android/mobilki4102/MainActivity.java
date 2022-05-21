package ru.cpt.android.mobilki4102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private EditText editTextRegisterLogin;
    private EditText editTextRegisterPassword;
    private Button buttonRegister;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        editTextLogin = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.login_button);
        editTextRegisterLogin = findViewById(R.id.login_register);
        editTextRegisterPassword = findViewById(R.id.password_register);
        buttonRegister = findViewById(R.id.register_button);
        textView = findViewById(R.id.text_view_error_login);


        // Обработчик на кнопку входа
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextLogin.getText().length()==0 || editTextPassword.getText().length()==0) {
                    textView.setText("Введите логин/пароль в поля входа");
                    return;
                }

                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();
                String savedPassword = sharedPreferences.getString(login, "");

                System.out.println("password: " + savedPassword);
                if (savedPassword.equals(password))
                    textView.setText("Вход успешен");
                else
                    textView.setText("Вход не успешен");
            }
        });


        // Обработчик на кнопку регистрации
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextRegisterLogin.getText().length()==0 || editTextRegisterPassword.getText().length()==0) {
                    textView.setText("Введите логин/пароль в поля регистрации");
                    return;
                }

                String newLogin = editTextRegisterLogin.getText().toString();
                String newPassword = editTextRegisterPassword.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (sharedPreferences.getString(newLogin, "").equals(newPassword)) {
                    textView.setText("Пользователь " + newLogin + " уже зарегистрирован");
                } else {
                    editor.putString(newLogin, newPassword);
                    editor.commit();
                    textView.setText("Пользователь " + newLogin + " зарегистрирован!");
                }
            }
        });
    }
}