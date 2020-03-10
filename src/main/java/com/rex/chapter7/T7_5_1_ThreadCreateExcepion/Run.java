package com.rex.chapter7.T7_5_1_ThreadCreateExcepion;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 22:24
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
        String username=null;
        System.out.println(username.hashCode());
    }
}
