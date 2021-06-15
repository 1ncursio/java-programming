package com.company;

import java.net.*;
import java.io.*;

public class HttpURLPostTest2 {
    public static void main(String[] args) throws Exception {
        String site = "http://localhost:9999";
        URL url = new URL(site);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("title=test").append("&description=본문").append("&author=1ncursio");

        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"), true);
        printWriter.println(stringBuffer.toString());

        int responseCode = connection.getResponseCode();
        System.out.println("Response code : " + responseCode);
        System.out.println(connection.getResponseMessage());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
