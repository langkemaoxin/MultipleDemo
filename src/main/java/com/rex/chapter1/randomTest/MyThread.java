package com.rex.chapter1.randomTest;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 19:15
 * @Version 1.0
 */
public class MyThread extends Thread {
    @Override
    public void run() {

        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) Math.random() * 1000;
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
