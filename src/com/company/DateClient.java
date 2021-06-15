package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DateClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9100);
            for (int i = 0; i < 1000; i++) {
                Thread threadClientSocket = new ThreadClientSocket(socket);
                threadClientSocket.start();
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadClientSocket extends Thread {
    Socket socket;

    public ThreadClientSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (Socket socket = new Socket("localhost", 9100)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}