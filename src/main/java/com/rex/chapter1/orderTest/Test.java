package com.rex.chapter1.orderTest;

public class Test{
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread(1);
        MyThread myThread2 = new MyThread(2);
        MyThread myThread3 = new MyThread(3);
        MyThread myThread4 = new MyThread(4);
        MyThread myThread5 = new MyThread(5);
        MyThread myThread6 = new MyThread(6);
        MyThread myThread7 = new MyThread(7);
        MyThread myThread8 = new MyThread(8);
        MyThread myThread9 = new MyThread(9);

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        myThread6.start();
        myThread7.start();
        myThread8.start();
        myThread9.start();


    }
}
