package com.company;

import java.net.*;
import java.io.*;

public class HttpURLTest {
    public static void main(String[] args) throws Exception{
        String site = "http://www.naver.com";
        URL url = new URL(site);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("Response code : " + responseCode);
        System.out.println(con.getResponseMessage());

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        StringBuffer buf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buf.append(line);
        }
        reader.close();

        System.out.println(buf);
    }
}
