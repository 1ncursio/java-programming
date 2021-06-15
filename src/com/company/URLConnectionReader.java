package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {
    public static void main(String[] args) {
        BufferedReader reader = null;
        URL site = null;
        try {
            site = new URL("https://www.naver.com");
            URLConnection url = site.openConnection();
            reader = new BufferedReader(new InputStreamReader(url.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (Exception ignore) {
            }
        }

    }
}
