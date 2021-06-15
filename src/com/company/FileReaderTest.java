package com.company;

import java.io.*;
import java.util.*;

public class FileReaderTest {
    public static void main(String[] args) {
        Map<String, String> dict = new HashMap<>();
        try (FileReader reader = new FileReader("dict.props")) {
            Properties props = new Properties();
            props.load(reader);

            Set<Map.Entry<Object, Object>> entrySet = props.entrySet();
            for (Map.Entry<Object, Object> entry : entrySet) {
                dict.put((String) entry.getKey(), (String) entry.getValue());
            }

            dict.forEach((key, value) -> System.out.println(key + " = " + value));

            try (FileWriter writer = new FileWriter("dict.props", true)) {
                writer.write("망고=mango");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
