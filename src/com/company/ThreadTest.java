package com.company;

public class ThreadTest {
    public static void main(String[] args) {
        Thread c1 = new Counter(1);
        Thread c2 = new Counter(11);

        c1.start();
        c2.start();

        System.out.println("main thread exited...");
    }
}

class Counter extends Thread{
    private int start;

    public Counter(int start) {
        this.start = start;
    }

    public void run() {
        for (int i = start; i < start + 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(start + "부터 " + (start + 10) + "까지 count 종료");
    }
}