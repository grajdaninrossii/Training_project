package com.example.postmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private float[] current_light;

    private Button startButton, stopButton;
    private SensorManager sensorManager;
    private TextView lightAngle; //текстовое поле для вывода информации


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.play);
        stopButton = (Button) findViewById(R.id.stop);
        lightAngle = (TextView) findViewById(R.id.lightValue);

        stopButton.setEnabled(false);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        current_light = new float[1];
        current_light[0] = 0;
    }

    @Override
    // Изменение показаний датчиков
    public void onSensorChanged(SensorEvent event) {
        loadSensorData(event); // получаем данные с датчика

        if (lightAngle == null) lightAngle = (TextView) findViewById(R.id.lightValue);

        //вывод результата
        lightAngle.setText(String.valueOf(Math.round(Math.toDegrees(current_light[0]))));

        if (Math.round(Math.toDegrees(current_light[0])) < 3000) {
            play(startButton);
        } else {
            stop(stopButton);
        }
    }

    @Override
    // Изменение точности показаний датчика
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //используется для получения уведомлений от SensorManager при изменении значений датчика
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void play(View view) {
        Intent i = new Intent(this, MediaService.class);
        startService(i);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    public void stop(View view) {
        Intent i = new Intent(this, MediaService.class);
        stopService(i);

        stopButton.setEnabled(true);
        startButton.setEnabled(false);
    }

    private void loadSensorData(SensorEvent event) {
        final int type = event.sensor.getType(); //определяем тип датчика
        if (type == Sensor.TYPE_LIGHT) { //если датчик освещения
            current_light = event.values.clone();
        }
    }
}