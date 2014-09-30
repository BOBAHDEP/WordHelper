package com.wordhellper;


public class Point {
    private int x;
    private int y;
    private char letter;

    public char getLetter() {
        return letter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(char letter, int x, int y) {
        this.letter = letter;
        this.x = x;
        this.y = y;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }


}
