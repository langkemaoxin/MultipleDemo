package com.rex.chapter7.T7_1_3_StateTest3;

import com.rex.chapter1.orderTest.MyThread;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 8:29
 * @Version 1.0
 *
 * 某一个线程在等待锁的时候，线程状态为：Blocked
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("a");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("b");
        myThread2.start();

        Thread.sleep(1000);


        System.out.println("t2.states=" + myThread2.getState());
    }
}

class MyService {
    synchronized static public void serviceMethod() {
        System.out.println(Thread.currentThread().getName() + "进入了业务的方法");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        MyService.serviceMethod();
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        MyService.serviceMethod();
    }
}



