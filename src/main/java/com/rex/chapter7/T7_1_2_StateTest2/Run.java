package com.rex.chapter7.T7_1_2_StateTest2;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 8:15
 * @Version 1.0
 *
 * 被sleep阻塞的线程的方法状态为： TIMED_WAITING
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();

        Thread.sleep(1000);

        System.out.println("myThread.getState="+myThread.getState());

    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("begin sleep");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end sleep");
    }
}