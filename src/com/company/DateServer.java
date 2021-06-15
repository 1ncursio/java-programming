package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class DateServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9100)) {
            Socket socket = null;
            while (true) {
                try {
                    // 클라이언트 요청 대기
                    System.out.println("waiting for client connection...");
                    socket = serverSocket.accept();
                    // 클라이언트 요청이 오면 새 socket 을 생성해 클라이언트와 연결함
                    System.out.println("New connection...[" + socket.getRemoteSocketAddress() + "]");
                    System.out.println(java.lang.Thread.activeCount());
                    Thread thread = new ThreadSocket(socket);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadSocket extends Thread {
    Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(Calendar.getInstance().getTime());
            socket.close();
            System.out.println("Disconnect with client...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception ignore) {

            }
        }
    }
}