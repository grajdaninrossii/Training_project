package com.example.morze;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private static final String LOG_TAG = "Main_Activity" ;
    private EditText signs;
    private EditText signCall; // Переопределить enter
    private InputMethodManager imm;
    private boolean immS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // запрет на смену ориентации

        signs = (EditText) findViewById(R.id.signs);


        // Показать клавиатуру при нажатии
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        immS = false;

//        signs.setOnClickListener();

        signs.setKeyListener(new KeyListener() {

            @Override
            public int getInputType() {
                Log.d("TEST_KEY", "getInputType");
                return 0;
            }

            @Override
            public synchronized boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {

                char myChar = (event.getDisplayLabel() + "").toUpperCase().charAt(0);
                Log.d("Test_Label", event.getDisplayLabel() + "");


                // Прописать передачу нужного символа
                if (keyCode == KeyEvent.KEYCODE_0) {
                    signs.setText("0");
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_1) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_2) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_3) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_4) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_5) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_6) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_7) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_8) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_9) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_A || event.getDisplayLabel() == 'А') {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_B || myChar == 1041) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_C || myChar == 1062) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_D || myChar == 1044) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_E || myChar == 1045) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_F || myChar == 1060) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_G || myChar == 1043) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_H || myChar == 1061) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_I || myChar == 1048) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_J || myChar == 1049) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_K || myChar == 1050) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_L || myChar == 1051) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_M || myChar == 1052) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_N || myChar == 1053) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_O || myChar == 1054) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_P || myChar == 1055) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_Q || myChar == 1065) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_R || myChar == 1056) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_S || myChar == 1057) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_T || myChar == 1058) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_U || myChar == 1059) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_V || myChar == 1046) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_W || myChar == 1042) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_X || myChar == 1068) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_Y || myChar == 1067) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (keyCode == KeyEvent.KEYCODE_Z || myChar == 1047) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (myChar == 1063) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (myChar == 1064) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (myChar == 1069) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (myChar == 1070) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (myChar == 1071) {
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
                    finish();
                } else if (keyCode == KeyEvent.KEYCODE_BREAK){
                    finish();
                    Log.d("TEST_KEY", Integer.toString(event.getKeyCode()));
                }

                String m = event.getDisplayLabel() + "";

//                Log.d("TEST_KEY", myChar + "");
//                for (char ch = 'А'; ch <= 'я'; ch++) {
//                    Log.d("TEST_KEY", ch + " (" + ((int) ch) + ")");
//                }

                return true;
            }

            // При поднятии
            @Override
            public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
//                Log.d("TEST_KEY", "onKeyUp");
                return false;
            }

            @Override
            public boolean onKeyOther(View view, Editable text, KeyEvent event) {
                Log.d("TEST_KEY", "onKeyOther");
                return false;
            }

            @Override
            public void clearMetaKeyState(View view, Editable content, int states) {
                Log.d("TEST_KEY", "clearMetaKeyState");

            }


        });


        // Обработка событий кнопок режимов
        RadioGroup setMode = (RadioGroup) findViewById(R.id.operationMode);

        // Переключение режимов
        setMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case -1:
                        Toast.makeText(getApplicationContext(), "Режим не выбран",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.receive:
                        Toast.makeText(getApplicationContext(), "Первый переключатель",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.broadcast:
                        Toast.makeText(getApplicationContext(), "Второй переключатель",
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

        // Получаем экземпляр элемента Spinner
        // Показывает, кто сидит на частотах (для значений можно добавить также частоту, на которой сидят пользователи)
        Spinner spinner = findViewById(R.id.otherParties);

        // Настраиваем адаптер
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.callSignName,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер
        spinner.setAdapter(adapter);

        // По выбранному позывному настраивается частота для связи
        // Я хз че делать, если челы сидят на одной частоте
        // Можно реализовать список
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Меняется частота в зависимости от выбранного варианта
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Кнопки изменения частоты
    public void changeFrequency(View v){

        ImageButton b = (ImageButton) v;
        TextView freq = (TextView) findViewById(R.id.frequency);
        int newV = Integer.valueOf(freq.getText().toString());

//        Log.d(LOG_TAG, "Тест:" + b.getId() + "Нужно: " + R.id.reduce_freq);

        // В зависимости от нажатой кнопки добавляем или уменьшаем частоту
        if (b.getId() == R.id.reduce_freq){
            if (newV > 0){ // Ограничитель
                newV -= 10;
                freq.setText(Integer.toString(newV));
            }
        } else if (b.getId() == R.id.above_freq){
            if (newV < 70000){
                newV += 10;
                freq.setText(Integer.toString(newV));
            }
        }
    }

    public void enterKey(View v){

        signs.requestFocus();
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        imm.showSoftInput(signs, InputMethodManager.SHOW_IMPLICIT);

//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    // Сохранение позывного
    public void saveCallSign (View v){
        Button b = (Button) v;

        // Здесь позывой сохраняется в базе.
    }

}