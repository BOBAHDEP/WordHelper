package com.wordhellper;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        field.print();
        Point[][] points = field.getPointArray(1, 1);
        int y = 0;
        for (String s: field.getWords()){
            if (Request.validate(s)) {
                System.out.println(s);
                y++;
            }
        }
        System.out.println(y + "words totally");
    }

}
