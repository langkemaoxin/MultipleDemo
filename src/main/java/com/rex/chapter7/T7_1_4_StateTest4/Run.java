package com.rex.chapter7.T7_1_4_StateTest4;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 22:24
 * @Version 1.0
 *
 * 当进入同步方法，并且给锁对象执行了wait方法时：
 *
 * 线程的状态是 wating
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        System.out.println(myThread.getState());
    }
}

class Lock {
    public static final Byte lock = new Byte("0");
}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            synchronized (Lock.lock) {
                Lock.lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
