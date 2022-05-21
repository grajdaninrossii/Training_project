package com.example.myapptest;

import android.content.Context;
import android.graphics.*;
import android.view.View;

import java.util.ArrayList;

public class MyDraw extends View {

    // Создаем три темы для рисовалки.
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();

    // Инициализируем массивы с координатами.
    int N = 10; // Кол-во шариков.
    float[] x = new float[N];
    float[] y = new float[N];

    float[] xx = new float[N];
    float[] yy = new float[N];

    // Скорость изменния для каждой точки массива
    float[] vx = new float[N];
    float[] vy = new float[N];

    float[] vxx = new float[N];
    float[] vyy = new float[N];
//    long lastTime = System.currentTimeMillis();
    boolean started; // Запуск инициализвации массива.

    // Линнии(координата по y) и скорость их инициализации
    ArrayList<Float> listX = new ArrayList<Float>();
//    float speedLine = (float)(Math.random() * 6 - 3);

    // Возвращает псевдослучайное число
    // в полуинтервале [min, max)
    float rand(float min , float max){
        return (float)(Math.random() * (max - min + 1)) + min;
    }

    // Заполняет массив псевдослучайными числами в полуинтервале [min, max)
    void fillRandom(float[] array , float min, float max){
        for (int i = 0; i < array.length; i++){
            array[i] = rand (min, max);
        }
    }

    void addMass(float[] x, float[] y, float[] xp, float[] yp, int size){
        for (int i = 0; i < N; i++){
            if (x[i] < 20 || x[i] > this.getWidth() - 20){ xp[i] = -xp[i];}
            if (y[i] < 20 || y[i] > this.getHeight() - 20){ yp[i] = -yp[i];}
            x[i] += xp[i];
            y[i] += yp[i];
        }
    }

    public MyDraw(Context context) {
        super(context);
        paint1.setColor(0xff0000ff);
        paint2.setColor(0xffff0000);
        paint3.setStrokeWidth(2);
    }

    // Рисовка и изменение координат точек
    public void moveBolls(Canvas canvas){
        for (int i = 0; i < N; i++){
            canvas.drawCircle(x[i], y[i], 20, paint1);
            canvas.drawCircle(xx[i], yy[i], 30, paint2);
        }
        addMass(x, y, vx, vy, 20);
        addMass(xx, yy, vxx, vyy, 30);
    }

    // Рисовка и изменение координат линий
    public void moveLine(Canvas canvas){
        for (int i = 0; i < listX.size(); i++){
            canvas.drawLine(0, listX.get(i), this.getWidth(), listX.get(i), paint3);
        }

        for (int i = 0; i < listX.size(); i++){
            if (listX.get(i) > this.getHeight()) {listX.set(i, (float)0);}
            listX.set(i,  rand(listX.get(i), listX.get(i) + 2));
//            listX.set(i, listX.get(i) + speedLine);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        // super.onDraw(canvas);

        //paint.setColor(0xffffff33);
        canvas.drawColor(0xffffff33);
//        int yx = 0;
//        while (yx < canvas.getHeight()){
//            canvas.drawLine(0, yx, this.getWidth(), yx, paint3);
//            yx += 30;
//        }

        // делаем анимацию.
        // Инициализируем массивы.
        if (!started){
            started = true;

            // Шарики
            fillRandom(x, 20, getWidth());
            fillRandom(y, 20, getHeight());

            fillRandom(xx, 30, getWidth());
            fillRandom(yy, 30, getHeight());

            fillRandom(vx, -3, 3);
            fillRandom(vy, -3, 3);

            fillRandom(vxx, 1, 3);
            fillRandom(vyy, 1, 3);

            // Линии
            float lineN = 0;
            while (lineN < canvas.getHeight() + 30){
//            canvas.drawLine(0, yx, this.getWidth(), yx, paint3);
                listX.add(lineN);
                lineN += 50;
            }
        }

        moveBolls(canvas);
        moveLine(canvas);

        // готовим x с учетом прошедшего времени
        // с момента последней перерисовки
//        long nowTime = System.currentTimeMillis();
//        x += 0.005f * (nowTime - lastTime);

        invalidate();
    }
}