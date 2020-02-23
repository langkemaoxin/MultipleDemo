package com.rex.chapter1.t6;

public class Run2 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //myThread.start();
        myThread.run();
    }
}

class MyThread extends Thread{
    public MyThread(){
        System.out.println("构造方法打印："+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印"+Thread.currentThread().getName());
    }
}
