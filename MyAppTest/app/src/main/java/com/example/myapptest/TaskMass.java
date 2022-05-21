package com.example.myapptest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskMass extends Activity implements OnClickListener, OnLongClickListener {


    private int WIDTH = 10;
    private int HEIGHT = 10;

    private Button[][] cells; // Создаем матрицу с кнопками.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();
    }

    void generate() {

        int num = 100;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[j][i].setText(num + "");
                num--;
                if (Math.random() >= 0.5) {
                    cells[i][j].setBackgroundColor(Color.YELLOW);
                }
            }
        }
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false); // Надувание кнопки в xml.
                cells[i][j].setOnClickListener(this); // Действия при разных нажатиях.
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(j + "," + i); // Устанавливаем координаты.
                cellsLayout.addView(cells[i][j]); // Добавление кнопки на поле.
            }
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        for (int x = 0; x < WIDTH; x++)
        {
            cells[tappedY][x].setBackgroundColor(Color.RED);
        }
        for (int y = 0; y < HEIGHT; y++)
        {
            cells[y][tappedX].setBackgroundColor(Color.RED);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
