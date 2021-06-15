package com.company;

import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HttpURLTest2 {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9999");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        System.out.println(con.getResponseCode());
        System.out.println(con.getResponseMessage());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line);
        }
        bufferedReader.close();

        Gson gson = new Gson();
        Post[] posts = gson.fromJson(buffer.toString(), Post[].class);

        for (Post post : posts) {
            System.out.println(post.toString());
        }

        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/scpark?useSSL=false", "root", "nodejsbook");
        Class.forName("org.mariadb.jdbc.Driver");
        String sql = "INSERT INTO `post` (id, title, description, author) VALUES (?, ?, ?, ?)";

        PreparedStatement pstmt = dbConnection.prepareStatement(sql);
        for (Post post : posts) {
            pstmt.setInt(1, post.getId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getDescription());
            pstmt.setString(4, post.getAuthor());
            pstmt.executeUpdate();
        }
    }
}