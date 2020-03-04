package com.rex.chapter3.T322_Join;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/4 8:32
 * @Version 1.0
 *
 * 演示了Join方法，可以等待一个线程结束
 */
public class Run {
    public static void main(String[] args) {

        try {
            MyThread myThread = new MyThread();
            myThread.start();

            myThread.join();
            System.out.println("任务结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        int value = (int) (Math.random() * 1000);
        System.out.println(value);
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}