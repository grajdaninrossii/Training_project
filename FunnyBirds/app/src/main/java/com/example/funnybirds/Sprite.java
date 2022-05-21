package com.example.funnybirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {

    private Bitmap bitmap; // Ссылка на изображение с набором кадров
    private List<Rect> frames; //Коллекция прямоугольных областей на изображении — кадры, которые участвуют в анимационной последовательности

    // Ширина и высоту кадра для отображения на экране
    private int frameWidth;
    private int frameHeight;

    private int currentFrame; // Номер текущего кадра в коллекции frames
    private double frameTime; // Время, отведенное на отображение каждого кадра анимационной последовательности
    private double timeForCurrentFrame; // Текущее время отображения кадра. Номер текущего кадра currentFrame меняется на следующий при достижении переменной timeForCurrentFrame значения из frameTime

    // Местоположение левого верхнего угла спрайта на экране
    private double x;
    private double y;

    // Скорости перемещения спрайта по оси X и Y соответственно
    private double velocityX;
    private double velocityY;

    private int padding; // Внутренний отступ от границ спрайта, необходимый для более точного определения пересечений спрайтов

    public Sprite (double x, double y, double velocityX, double velocityY, Rect initialFrame, Bitmap bitmap){
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.bitmap = bitmap;
        this.frames = new ArrayList<Rect>();
        this.frames.add(initialFrame);
//        this.bitmap = bitmap;
        this.timeForCurrentFrame = 0.0;
        this.frameTime = 0.1;
        this.currentFrame = 0;
        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
        this.padding = 20;
    }


    // Добавление кадров в последовательность
    public void addFrame (Rect frame) {
        frames.add(frame);
    }

    // Обновление внутреннего состояния спрайта
    public void update (int ms){

        timeForCurrentFrame += ms;

        if (timeForCurrentFrame >= frameTime){
            currentFrame = (currentFrame + 1) % frames.size(); // переход от последнего к нулевому кадру
            timeForCurrentFrame = timeForCurrentFrame - frameTime;
        }

        x += velocityX * ms / 1000.0;
        y += velocityY * ms / 1000.0;
    }

    // Рисование спрайта на холсте
    public void draw (Canvas canvas){
        Paint paint = new Paint();
        Rect destination = new Rect((int) x, (int) y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination, paint);
    }

    // Определение столкновения: рисуем прямоугольные области.
    public Rect getBoundingBoxRect() {
        return new Rect ((int) x + padding, (int) y + padding, (int)(x + frameWidth - 2 *padding),
                (int)(y + frameHeight - 2* padding));
    }

    // Определение столкновения: определение пересечения спрайтов
    public boolean intersect (Sprite s){
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }

    // Ниже геттеры и сеттеры
    public void setX(double x) {
        this.x = x;
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getVx() {
        return velocityX;
    }

    public void setVx(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVy() {
        return velocityY;
    }

    public void setVy(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame%frames.size();
    }

    public double getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(double frameTime) {
        this.frameTime = Math.abs(frameTime);
    }

    public double getTimeForCurrentFrame() {
        return timeForCurrentFrame;
    }

    public void setTimeForCurrentFrame(double timeForCurrentFrame) {
        this.timeForCurrentFrame = Math.abs(timeForCurrentFrame);
    }

    public int getFramesCount () {
        return frames.size();
    }
}
