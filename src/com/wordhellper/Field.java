package com.wordhellper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Field {
    private static final int FIELD_SIZE = 5;
    private static final int WORD_LENGTH = 4;

    private Point[][] points = new Point[FIELD_SIZE][FIELD_SIZE];

    public Field() {
        Point[][] res = new Point[FIELD_SIZE][FIELD_SIZE];
        Scanner sc = new Scanner(System.in);
        char letter;
        while (true) {
            System.out.println("Please, enter one letters from matrix:");
            for (int i = 0; i < FIELD_SIZE; i++) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    try {
                        System.out.println("a[" + i + "][" + j + "] = ");
                        letter = sc.next().charAt(0);
                        res[i][j] = new Point(letter, i, j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (int i = 0; i < FIELD_SIZE; i++) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    System.out.print(res[i][j].getLetter());
                }
                System.out.println();
            }
            System.out.println("Matrix is OK? (y/n)");
            letter = sc.next().charAt(0);
            if (letter == 'y'){
                this.points = res;
                return;
            }
        }
    }

    public void print(){
        for (int i = 0; i < FIELD_SIZE; i++){
            for (int j = 0; j < FIELD_SIZE; j++){
                System.out.print(points[i][j].getLetter() + " ");
            }
            System.out.println("");
        }
    }

    private Point getPoint(int x, int y){
        if (x < 0|| y < 0 || x >= FIELD_SIZE || y >= FIELD_SIZE){
            return null;
        }
        return points[x][y];
    }

    private Point[] getPointsAround(Point point){
        Point[] res = new Point[8];
        int n = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i != 0 || j != 0){
                    if (point.getLetter() != '*') {
                        res[n] = getPoint(point.getX() + i, point.getY() + j);
                    }
                    n++;
                }
            }
        }
        return res;
    }

    private boolean check(Point point, List<Point> arrayList){
        if (point == null){
            return true;
        }
        for (Point point1: arrayList){
            if (point1.equals(point)){
                return true;
            }
        }
        return false;
    }

    public Point[][] getPointArray(int x, int y){
        Point[][] res = new Point[WORD_LENGTH][(int)Math.pow(8,WORD_LENGTH)];
        res[0][0] = points[x][y];
        for (int i = 0; i < WORD_LENGTH-1; i++){
            for (int j = 0; j < (int)Math.pow(8,i); j++){
                for (int l = 0; l < 8; l++){
                    res[i + 1][8 * j + l] = getPointsAround(res[i][j])[l] != null ? getPointsAround(res[i][j])[l] : new Point('*', -1, -1);
                }
            }
        }

        for (int i = 0;i < WORD_LENGTH; i++){
            for (int j = 0; j < (int)Math.pow(8,WORD_LENGTH); j++){
                if (res[i][j] == null){
                    res[i][j] = new Point('*',-1,-1);
                }
            }
        }

        return res;
    }

    public List<String> getWords(){
        List<String> res = new ArrayList<String>();
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points.length;y++) {
                Point[][] points = getPointArray(x, y);
                Point[] word = new Point[WORD_LENGTH];

                Boolean b;

                for (int j = 0; j < (int) Math.pow(8, WORD_LENGTH); j++) {
                    b = false;
                    for (int i = 0; i < WORD_LENGTH; i++) {
                        word[i] = points[i][j / (int) Math.pow(8, WORD_LENGTH - i - 1)];
                        if (word[i].getLetter() == '*') {
                            b = true;
                        }
                        for (int k = 0; k < i; k++) {
                            if (word[k].equals(word[i])) {
                                b = true;
                            }
                        }
                    }
                    if (!b) {
                        char[] charRes = new char[WORD_LENGTH];
                        for (int k = 0; k < WORD_LENGTH; k++) {
                            charRes[k] = word[k].getLetter();
                        }
                        res.add(new String(charRes));
                    }
                }
            }
        }
        return res;
    }

}
