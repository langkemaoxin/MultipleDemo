package com.rex.chapter1.T1101;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 11:51
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("Main thread begin prioprity="+Thread.currentThread().getPriority());

        Thread.currentThread().setPriority(7);
        
        System.out.println("Main thread begin prioprity="+Thread.currentThread().getPriority());
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();


    }
}


class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread1 run priority="+this.getPriority());
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread2run priority="+this.getPriority());
    }
}