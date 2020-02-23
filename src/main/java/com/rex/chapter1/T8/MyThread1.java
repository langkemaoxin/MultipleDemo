package com.rex.chapter1.T8;

/**
 * @ClassName MyThread1
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/19 22:42
 * @Version 1.0
 */
public class MyThread1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName=" + this.getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName=" + this.getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run1 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        System.out.println("begin=" + System.currentTimeMillis());
        myThread1.run();
        System.out.println("end=" + System.currentTimeMillis());
    }
}
