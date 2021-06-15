package com.company;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("ex.props");
        Properties properties = new Properties();
        properties.load(reader);

        System.out.println(properties.get("a"));
        System.out.println(properties.get("b"));
        System.out.println(properties.get("c"));
        System.out.println(properties.get("d"));
    }
}
