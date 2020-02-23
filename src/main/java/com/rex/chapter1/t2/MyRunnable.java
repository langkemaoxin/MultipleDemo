package com.rex.chapter1.t2;

/**
 * @ClassName MyRunnable
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 19:31
 * @Version 1.0
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunable运行中...");

        System.out.println(Thread.currentThread().getName());

    }
}

class Test {
    public static void main(String[] args) {

        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable,"Tommy");
        thread.start();

        System.out.println("运行结束！！！");
    }
}
