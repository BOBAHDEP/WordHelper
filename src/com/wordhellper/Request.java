package com.wordhellper;

import java.io.*;
import java.net.*;

public class Request {

    private static String getHTML(String text) {
        String urlToRead = "http://dictionary.yandex.net/dicservice.json/lookup?callback=ya_.json.c(11)&ui=ru&text="+text+"&lang=en-ru&flags=1&sid=546092b1";
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validate(String word){
        if (getHTML(word).equals("ya_.json.c(11)({\"head\":{},\"def\":[]})")){
            return false;
        }
        return true;
    }

    public static void main(String args[])
    {
        Request c = new Request();
        System.out.println(c.getHTML("fgys"));
        System.out.println(c.getHTML("word"));
    }
}