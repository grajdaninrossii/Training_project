package com.example.myapptest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.IllegalFormatWidthException;
import java.util.Random;

public class PlayColorLayout extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private static final String LOG_TAG = "Play_Color_Layout";

    DisplayMetrics displaymetrics = new DisplayMetrics();
    private int screenWidth;
    private int screenHeight;

    int [] color = {Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW,
            Color.GRAY, Color.GREEN, Color.WHITE};
    Random rnd = new Random();

    private int countButton = 5; // 10 * 10

    private Button[][] cells;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Creating view..");
        setContentView(R.layout.play_color_cells);
        Log.d(LOG_TAG, "Created view");

        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        // Настраиваем размер кнопок
//        Button btn_size_set = (Button) findViewById(R.id.ButtonCell);
//        btn_size_set.getLayoutParams().width = LayoutParams.WRAP_CONTENT;

        makeCells();

        getrate();
    }

    private void getrate() {
        int num = 100;

        for (int i = 0; i < countButton; i++) {
            for (int j = 0; j < countButton; j++) {
                cells[j][i].setText(num + "");
                num--;
                cells[i][j].setBackgroundColor(color[rnd.nextInt(7)]);
            }
        }
    }

    private void makeCells() {
        cells = new Button[countButton][countButton];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(countButton);
        for (int i = 0; i < countButton; i++){
            for (int j = 0; j < countButton; j++){
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.play_color_cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this); // Действия при разных нажатиях.
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(j + "," + i); // Устанавливаем координаты.
                cellsLayout.addView(cells[i][j]); // Добавление кнопки на поле.

            }
        }
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }
    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

//        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        int setColor = color[rnd.nextInt(7)];
        for (int x = 0; x < countButton; x++)
        {
            cells[tappedY][x].setBackgroundColor(setColor);
        }
        for (int y = 0; y < countButton; y++)
        {
            cells[y][tappedX].setBackgroundColor(setColor);
        }

        checkWin();
    }

    private void checkWin() {
        boolean win = true;
        ColorDrawable colorCell = (ColorDrawable) cells[0][0].getBackground();
        for (int i = 0; i < countButton; i++) {
            for (int j = 0; j < countButton; j++) {
                if (colorCell.getColor() != ((ColorDrawable)cells[i][j].getBackground()).getColor()){
//                    cells[0][0].setBackgroundColor(Color.RED);
                    win = false;
                    break;
                }
            }
        }
        if (win){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You win!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
