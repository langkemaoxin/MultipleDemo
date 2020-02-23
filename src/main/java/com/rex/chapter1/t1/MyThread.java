package com.rex.chapter1.t1;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 19:11
 * @Version 1.0
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("MyThread");
    }
}

