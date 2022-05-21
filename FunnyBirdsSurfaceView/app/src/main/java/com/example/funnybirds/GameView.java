package com.example.funnybirds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class GameView extends Thread {

    // Размеры игрового поля
    private int viewWidth;
    private int viewHeight;

    private int points = 0; // очки, набранные игроком
    private int speedBird = 150; // скорость птицы (аватара)
    private int speedEnemyBonus = -300;
//    private int speedBonus = -200;
    private boolean endGame = false;
    public boolean onPause = false;

    private Sprite playerBird; // гланый герой (аватар)
    private Sprite enemyBird; // вражеская птица
    private Sprite bonusBird; // бонус
    private Sprite bombBird;

    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private long mPrevRedrawTime;

    private final int timerInterval = 30; // время интервала обновления спрайтов
    private long mStartTime;
    private final int REDRAW_TIME = 30;

    public GameView(SurfaceHolder surfaceHolder, Resources resources) {
        this.surfaceHolder = surfaceHolder;

        // Загружаем картинку с птичкой
        Bitmap b = BitmapFactory.decodeResource(resources, R.drawable.player);
        Bitmap e = BitmapFactory.decodeResource(resources, R.drawable.enemy);

        // Бонус
        Bitmap bonus = BitmapFactory.decodeResource(resources, R.drawable.bonus);


        int w = bonus.getWidth();
        int h = bonus.getHeight();

        Rect bonusFrame = new Rect(0, 0, w, h);
        double max = 3, min = 1;
        Random setStartPosition = new Random();
        bonusBird = new Sprite((setStartPosition.nextDouble() * (max - min ) + min) * 1000, Math.random() * 1000, speedEnemyBonus, 0, bonusFrame, bonus);

        // Вырезаем птичку из картинки
        int width = b.getWidth() / 5;
        int height = b.getHeight() / 3;

        Rect firstFrame = new Rect(0, 0, width, height);

        // Птица
        playerBird = new Sprite(10, 0,0,  speedBird, firstFrame, b);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                if (i == 2 && j == 3){
                    continue;
                }
                playerBird.addFrame(new Rect(j * width, i * height, j * width + width, i * width + width));
            }
        }

        // Бомбовые птицы
        Bitmap bomb = BitmapFactory.decodeResource(resources, R.drawable.enemy_bomb);

        firstFrame = new Rect(4 * width, 0, 5 * width, height);
        bombBird = new Sprite(4000, 250, speedEnemyBonus, 0, firstFrame, bomb);

        // Добавляем вражескую птицу
        // Вырезаем птичку из картинки
        firstFrame = new Rect(4 * width, 0, 5 * width, height);
        enemyBird = new Sprite(2000, 250, speedEnemyBonus, 0, firstFrame, e);

        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {
                if (i ==0 && j == 4) {
                    continue;
                }
                if (i ==2 && j == 0) {
                    continue;
                }
                enemyBird.addFrame(new Rect(j * width, i * height, j * width + width, i * width + width));
                bombBird.addFrame(new Rect(j * width, i * height, j * width + width, i * width + width));
            }
        }

//        // Создадим и запустим таймер
//        Timer t = new Timer ();
//        t.start();
    }

    public void setRunning(boolean run){
        runFlag = run;
        mPrevRedrawTime = getTime();
    }

    public long getTime() {
        return System.nanoTime() / 1_000_000;
    }

    @Override
    public void run() {
        Canvas canvas;
        mStartTime = getTime();

        while (true) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    onUpdate();
                    draw(canvas);
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }

        }
    }

    protected void draw(Canvas canvas) {

        canvas.drawARGB(250, 127, 199, 255);
        Paint paint  = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(55.0f);
        paint.setColor(Color.RED);

        if (onPause && !endGame) {
            canvas.drawText("Pause", canvas.getWidth() * 2 / 5, canvas.getHeight() / 2, paint);
            playerBird.setVy(0); // гланый герой (аватар)
            enemyBird.setVx(0); // вражеская птица
            bonusBird.setVx(0); // бонус
            bombBird.setVx(0);
        }

        if (endGame){
            canvas.drawText("Game over!", canvas.getWidth() * 2 / 5, canvas.getHeight() / 2, paint);
            playerBird.setVy(0); // гланый герой (аватар)
            enemyBird.setVx(0); // вражеская птица
            bonusBird.setVx(0); // бонус
            bombBird.setVx(0);
        }

        paint.setColor(Color.WHITE);
        canvas.drawText(points + "", canvas.getWidth() - 100, 70, paint);

        // Отрисовка спрайта
        // Размеры игрового поля
        viewWidth = canvas.getWidth();
        viewHeight = canvas.getHeight();
        playerBird.draw(canvas);
        enemyBird.draw(canvas);
        bonusBird.draw(canvas);
        bombBird.draw(canvas);
    }


    // Обновление состояния спрайта
    protected void onUpdate() {
        playerBird.update(timerInterval);
        enemyBird.update(timerInterval);
        bonusBird.update(timerInterval);
        bombBird.update(timerInterval);

        // Смена направления полета при касании концов экрана
        if (playerBird.getY() + playerBird.getFrameHeight() > viewHeight) {
            playerBird.setY(viewHeight - playerBird.getFrameHeight());
            playerBird.setVy(-playerBird.getVy());
            points--;
        } else if (playerBird.getY() < 0){
            playerBird.setY(0);
            playerBird.setVy(-playerBird.getVy());
            points--;
        }

        // Возвращение вражеской птицы
        if (enemyBird.getX() < - enemyBird.getFrameWidth()) {
            teleportEnemy ();
            points += 10; // за облет птицы игроку начисляются очки
            enemyBird.setVx(enemyBird.getVx() - 7); // увеличение скорости птиц при каждом проходе
            if (playerBird.getVy() > 0){
                playerBird.setVy(playerBird.getVy() + 7);
            } else {
                playerBird.setVy(playerBird.getVy() - 7);
            }

        }
        // Возвращение бомбы
        if (enemyBird.getX() < - enemyBird.getFrameWidth()) {
            teleportBombBird();
            points += 10; // за облет птицы игроку начисляются очки
        }

        if (bonusBird.getX() <- bonusBird.getFrameWidth()) {
            teleportBonus();
        }

        // Проверка столкновений
        if (enemyBird.intersect(playerBird)) {
            teleportEnemy ();
//            points -= 40;
            gameOver();
        }

        if (bombBird.intersect(playerBird)){
            teleportBombBird();
            gameOver();
        }

        if (bonusBird.intersect(playerBird)){
            teleportBonus();
            points += 20;
        }

    }

    // Возвращение птицы противника на экран
    private void teleportEnemy(){
        enemyBird.setX(viewWidth + Math.random() * 500);
        enemyBird.setY(Math.random() * (viewHeight - enemyBird.getFrameHeight()));
    }

    private void teleportBombBird (){
        bombBird.setX(viewWidth + Math.random() * 1500);
        bombBird.setY(Math.random() * (viewHeight - bombBird.getFrameHeight()));
    }

    private void teleportBonus(){
        int min = 1, max = 3;
        double setSizeTime = (Math.random() * (max - min ) + min) * 1000;
        bonusBird.setX(viewWidth + setSizeTime);
        bonusBird.setY(Math.random() * (viewHeight - bonusBird.getFrameHeight()));
    }

    // Движение птицы
    public boolean onTouchScreen(MotionEvent event) {
        int eventAction = event.getAction();

        // Если касание после конца игры.
        if (endGame){
            endGame = false;
            playerBird.setVy(speedBird); // гланый герой (аватар)
            enemyBird.setVx(speedEnemyBonus); // вражеская птица
            bonusBird.setVx(speedEnemyBonus); // бонус
            bombBird.setVx(speedEnemyBonus);
            playerBird.setY(10);
            teleportBombBird();
            teleportEnemy();
            teleportBonus();
        } else if (onPause) {
            onPause = false;
            playerBird.setVy(speedBird); // гланый герой (аватар)
            enemyBird.setVx(speedEnemyBonus); // вражеская птица
            bonusBird.setVx(speedEnemyBonus); // бонус
            bombBird.setVx(speedEnemyBonus);
        } else if (event.getX() > bombBird.getX() && event.getX() < bombBird.getX() + bombBird.getFrameWidth() &&
                event.getY() > bombBird.getY() && event.getY() < bombBird.getY() + bombBird.getFrameHeight()){
            teleportBombBird();
            points += 5;
        } else{
            if (eventAction == MotionEvent.ACTION_DOWN) {
                // Движение вверх
                if (event.getY() < playerBird.getBoundingBoxRect().top) {
                    playerBird.setVy(-Math.abs(playerBird.getVy()));
                    points--; // Расходуются очки при смене птицей направления полета
                } else if (event.getY() > playerBird.getBoundingBoxRect().bottom) {
                    playerBird.setVy(Math.abs(playerBird.getVy()));
                    points--;
                }
            }
        }
        return true;
    }

    // Обработка конца игры
    public void gameOver(){
        playerBird.setVy(0); // гланый герой (аватар)
        enemyBird.setVx(0); // вражеская птица
        bonusBird.setVx(0); // бонус
        bombBird.setVx(0);
        endGame = true;
        points = 0;
    }
}
