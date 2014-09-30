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
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please, enter one letters from matrix:");
//        for (int i = 0; i < FIELD_SIZE; i++){
//            for (int j = 0; j < FIELD_SIZE; j++){
//                try {
//                    System.out.println("a[" + i + "][" + j + "] = ");
//                    char letter = sc.next().charAt(0);
//                    res[i][j] = new Point(letter, i, j);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
        res[0][0] = new Point('q',0,0);
        res[0][1] = new Point('w',0,1);
        res[0][2] = new Point('e',0,2);
        res[0][3] = new Point('r',0,3);
        res[0][4] = new Point('t',0,4);
        res[1][0] = new Point('y',1,0);
        res[1][1] = new Point('u',1,1);
        res[1][2] = new Point('i',1,2);
        res[1][3] = new Point('o',1,3);
        res[1][4] = new Point('p',1,4);
        res[2][0] = new Point('a',2,0);
        res[2][1] = new Point('s',2,1);
        res[2][2] = new Point('d',2,2);
        res[2][3] = new Point('f',2,3);
        res[2][4] = new Point('g',2,4);
        res[3][0] = new Point('h',3,0);
        res[3][1] = new Point('j',3,1);
        res[3][2] = new Point('k',3,2);
        res[3][3] = new Point('l',3,3);
        res[3][4] = new Point('a',3,4);
        res[4][0] = new Point('z',4,0);
        res[4][1] = new Point('x',4,1);
        res[4][2] = new Point('c',4,2);
        res[4][3] = new Point('v',4,3);
        res[4][4] = new Point('b',4,4);

        this.points = res;
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

    private Point[][] getPointArray(int x, int y){
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

    public List<String> getWords(int x, int y){
        Point[][] points = getPointArray(x,y);
        Point[] word = new Point[WORD_LENGTH];
        List<String> res = new ArrayList<String>();
        Boolean b;

        for (int j = 0; j < (int)Math.pow(8,WORD_LENGTH); j++){
            b = false;
            for (int i = 0;i < WORD_LENGTH; i++){
                word[i] = points[i][j/(int)Math.pow(8,WORD_LENGTH-i-1)];
                if (word[i].getLetter() == '*'){
                    b = true;
                }
                for (int k = 0; k < i; k++){
                    if (word[k].equals(word[i])){
                        b = true;
                    }
                }
            }
            if (!b){
                char[] charRes = new char[WORD_LENGTH];
                for (int k = 0; k < WORD_LENGTH; k++){
                    charRes[k] = word[k].getLetter();
                }
                res.add(new String(charRes));
            }
        }
        return res;
    }

}
