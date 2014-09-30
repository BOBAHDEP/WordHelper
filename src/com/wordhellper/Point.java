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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }


}
