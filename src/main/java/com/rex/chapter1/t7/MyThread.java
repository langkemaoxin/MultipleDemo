package com.rex.chapter1.t7;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/19 22:31
 * @Version 1.0
 */
public class MyThread extends  Thread {
    @Override
    public void run() {
        System.out.println("Run=="+this.isAlive());
    }
}

class Run{
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("begin=="+myThread.isAlive());
        myThread.start();

        Thread.sleep(1000);
        System.out.println("end=="+myThread.isAlive());
    }
}
