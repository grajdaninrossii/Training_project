package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Main_Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Creating view..");
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Created view!");

    }
    //Описываем процесс перехода с MainActivity в SecondActivity,
    // который будет происходить при нажатии на нашу кнопку:
    public void Click(View view) {
        //Создаем переход:
        Intent intent=new Intent(MainActivity.this, PlayColorLayout.class);
        //Запускаем его при нажатии:
        startActivity(intent);
    }

    /** Вызывается при нажатии пользователем на кнопку Решить */
    public void solveEquation(View view) {
        // ax+b=c
        //  ax2+bx+c=0
        double a = Double.parseDouble(((EditText)
                findViewById(R.id.enterText_a)).getText().toString());
        double b = Double.parseDouble( ((EditText)
                findViewById(R.id.enterText_b)).getText().toString());
        double c = Double.parseDouble( ((EditText)
                findViewById(R.id.enterText_c)).getText().toString());
        TextView result = (TextView) findViewById(R.id.text_answer);

        if (a == 0 && b == 0 && c == 0){
            result.setText("0");
        } else {
            double d = b * b - 4 * c * a;
            Log.d(LOG_TAG, String.valueOf(b));
            if (d < 0){
                result.setText("Нет корней!");
            } else if (a == 0){
                result.setText("x = " + (-c/ b));
            } else {
                double x1 = (-1 *b + Math.sqrt(d))/ (2 * a);
                double x2 = (-1 *b - Math.sqrt(d))/ (2 * a);

                result.setText("Ответ:\n" + "x1 = " + String.valueOf(x1) + "\nx2 = " + String.valueOf(x2));
            }
        }
    }


}