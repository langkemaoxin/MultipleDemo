package com.rex.chapter1.T17;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/19 22:53
 * @Version 1.0
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();

        for (int i = 0; i < 10000000; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i=" + (i + 1));
        }
    }
}


class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        myThread.stop();

    }
}
