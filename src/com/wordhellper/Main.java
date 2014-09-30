package com.wordhellper;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        field.print();
//        Point[][] points = field.getCharArray(1,1);

//        for (int j =0; j < points[0].length; j++){
//            for (int i = 0; i < points.length; i++){
//                if (points[i][j] == null){
//                    System.out.print("*");
//                }else
//                System.out.print(points[i][j].getLetter());
//            }
//            System.out.println("");
//        }
        for (String s: field.getWords(2,2)){
            System.out.println(s);
        }


    }

}
