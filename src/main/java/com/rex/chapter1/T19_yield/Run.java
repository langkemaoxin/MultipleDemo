package com.rex.chapter1.T19_yield;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 11:45
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {

        long beginTime = System.currentTimeMillis();
        int count=0;
        for (int i = 0; i < 5000000; i++) {
            Thread.yield();
            count=count+(i+1);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("耗时"+(endTime-beginTime));
    }
}
