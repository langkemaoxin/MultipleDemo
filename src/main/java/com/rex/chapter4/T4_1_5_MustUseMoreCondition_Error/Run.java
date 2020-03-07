package com.rex.chapter4.T4_1_5_MustUseMoreCondition_Error;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 8:54
 * @Version 1.0
 * <p>
 *
 * 演示效果：
 * begin awaitA
 * begin awaitB
 * signalALL 时间为：1583544199915
 * end awaitA
 * end awaitB
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.start();

        ThreadB threadB = new ThreadB(myService);
        threadB.start();
        Thread.sleep(3000);

        myService.signalALL();

    }
}

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA");
            condition.await();
            System.out.println("end awaitA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB");
            condition.await();
            System.out.println("end awaitB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalALL() {
        try {
            lock.lock();
            System.out.println("signalALL 时间为：" + System.currentTimeMillis());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadA extends Thread {
    private MyService myService;

    ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.awaitA();
    }
}

class ThreadB extends Thread {
    private MyService myService;

    ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.awaitB();
    }
}