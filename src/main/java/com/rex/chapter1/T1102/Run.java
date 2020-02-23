package com.rex.chapter1.T1102;

import java.util.Random;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 12:11
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            MyThread1 myThread1 = new MyThread1();
            myThread1.setPriority(10);
            myThread1.start();

            MyThread2 myThread2 = new MyThread2();
            myThread2.setPriority(9);
            myThread2.start();
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {

        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 500000; j++) {
                Random random = new Random();
                random.nextInt();
                addResult = addResult + j;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("########### Thread1 use" + endTime);
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {

        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 500000; j++) {
                Random random = new Random();
                random.nextInt();
                addResult = addResult + j;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("___________ Thread1 use" + endTime);
    }
}


