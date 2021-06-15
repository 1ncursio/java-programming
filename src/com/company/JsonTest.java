package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

import com.google.gson.*;

public class JsonTest {
    public static void main(String[] args) throws Exception {
        String site = "https://jsonplaceholder.typicode.com/posts";
        URL url = new URL(site);

        URLConnection con = url.openConnection();

        InputStream inputStream = con.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            stringBuffer.append(line);
        }

        Gson gson = new Gson();
        Post[] posts = gson.fromJson(stringBuffer.toString(), Post[].class);

        for (Post post : posts) {
            System.out.println(post.toString());
        }
        insertIntoDB(posts);

        bufferedReader.close();
    }

    private static void insertIntoDB(Post[] posts) {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/scpark?useSSL=false", "root", "nodejsbook")) {
            Class.forName("org.mariadb.jdbc.Driver");
            String sql = "INSERT INTO posts(userId, id, title, body) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            for (Post post : posts) {
//                pstmt.setInt(1, post.getUserId());
//                pstmt.setInt(2, post.getId());
//                pstmt.setString(3, post.getTitle());
//                pstmt.setString(4, post.getBody());
//                pstmt.executeUpdate();
//            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}