package com.rex.chapter1.T4;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 22:05
 * @Version 1.0
 */
public class MyThread extends Thread {
    private int count = 5;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由" + this.currentThread().getName() + "计算,count=" + count);

    }
}

class test {
    public static void main(String[] args) {
        MyThread myThread = new  MyThread();
        Thread A = new Thread(myThread, "A");
        Thread B = new Thread(myThread, "B");
        Thread C = new Thread(myThread, "C");
        Thread D = new Thread(myThread, "D");
        Thread E = new Thread(myThread, "E");

        A.start();
        B.start();
        C.start();
        D.start();
        E.start();


    }
}