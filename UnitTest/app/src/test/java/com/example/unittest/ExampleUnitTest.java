package com.example.unittest;

import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    int testArray[][] = {{1,2},{1,2}};

    @Test
    public void testCreateMatrix(){
        assertArrayEquals( MainActivity.createMatrix(1, 1), new int[1][1]);
    }

    @Test
    public void testMatrixToString(){
        assertNotEquals( MainActivity.matrixToString(testArray),"1 2 1 2");
    }

    @Test
    public void testOnCreateOptionsMenu(){
    }

    @Test
    public void onOptionsItemSelected(){
    }
}