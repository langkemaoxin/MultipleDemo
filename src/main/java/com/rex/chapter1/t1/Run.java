package com.rex.chapter1.t1;

public class Run {
    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.start();
//        System.out.println("运行结束！");

        Runnable r = () -> {
            System.out.println("hi111");
        };

        Thread thread = new Thread(r);
        thread.start();
    }

}
