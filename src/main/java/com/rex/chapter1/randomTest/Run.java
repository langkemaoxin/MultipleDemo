package com.rex.chapter1.randomTest;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 19:19
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        try {

            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();

            for (int i = 0; i < 10; i++) {
                int time = (int) Math.random() * 1000;
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
