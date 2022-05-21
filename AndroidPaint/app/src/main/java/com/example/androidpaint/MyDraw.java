package com.example.androidpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class MyDraw extends View {

    public MyDraw (Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setSubpixelText(true); // Субпиксельное сглаживание для текста
        paint.setAntiAlias(true); // Антиальясинг, сглаживание  линий

        createHome(canvas, paint);
    }

    public void createHome(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);

        // Рисуем траву.
        canvas.drawRect(0, getHeight() / 3 * 2, getWidth(), getHeight(), paint);

        // Рисуем линию.
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, getHeight() / 3 * 2, getWidth(), getHeight() / 3 * 2, paint);

        // Рисуем дом.
        // создаём пустой прямоугольник и задаём координаты верхней левой и нижней правой точек
        RectF myRect = new RectF();
        myRect.set(getWidth() / 6, getHeight() / 2, getWidth() / 2, getHeight() * 3 / 4);

        // кисть
        paint.setColor(Color.parseColor("#B58764"));
        canvas.drawRoundRect(myRect, 25, 25, paint);

        // граница
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRoundRect(myRect, 25, 25, paint);

        int step = getHeight() / 4 / 8;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        // Делаем сайдинг.
        for (int y = getHeight() / 2 + step; y < getHeight() * 3 / 4 - step; y += step){
            canvas.drawLine(getWidth() / 6, y,  getWidth() / 2, y, paint);
        }

        // Делаем окно.
        // создаём пустой прямоугольник и задаём координаты верхней левой и нижней правой точек
        Rect myWindow = new Rect();
        myWindow.set(getWidth() * 3 / 13, getHeight() * 4 / 7, getWidth() * 3/ 7, getHeight() * 2 / 3);

        // кисть
        paint.setColor(Color.parseColor("#B0DEF5"));
        canvas.drawRect(myWindow, paint);

        // граница
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#4A423A"));
        paint.setStrokeWidth(11);
        canvas.drawRect(myWindow, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(getWidth() * 3 / 13, getHeight() * 13 / 21,getWidth() * 3/ 7, getHeight() * 13 / 21, paint);
        canvas.drawLine(getWidth() * 30 / 91,getHeight() * 4 / 7 , getWidth() * 30 / 91, getHeight() * 2 / 3, paint);

        // Крыша
        Paint paintStroke = new Paint();
        paintStroke.setStrokeWidth(3);
        paintStroke.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#B58764"));

        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();


        // Класс для создания точки
        class Pt {
            float x, y;

            Pt(float _x, float _y) {
                x = _x;
                y = _y;
            }
        }

        // создаём массив точек
        Pt[] myPath = { new Pt(getWidth() / 9, getHeight() / 2),
                        new Pt(getWidth() * 30 / 91, getHeight() / 3),
                        new Pt(getWidth() * 5/ 9, getHeight() / 2)
        };
        // переходим в первую точку рисования
        path.moveTo(myPath[0].x, myPath[0].y);

        // рисуем отрезки по заданным точкам
        for (int i = 1; i < myPath.length; i++){
            path.lineTo(myPath[i].x, myPath[i].y);
        }
        path.lineTo(myPath[0].x, myPath[0].y);

        // выводим результат
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paintStroke);

        // Чердак
        Rect attic = new Rect(getWidth() * 2 / 7, getHeight() / 2, getWidth() * 5/ 13, getHeight() * 11 / 25);
        canvas.drawRect(attic, paintStroke);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        step = getWidth() * 9 / 91 / 6;

        // Рельеф
        for (int x = getWidth() * 2 / 7; x < getWidth() * 5 / 13; x += step){
            canvas.drawLine(x, getHeight() / 2, x, getHeight() * 11 / 25, paint);
        }

    }
}