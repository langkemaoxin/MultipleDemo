package com.rex.chapter4.T4_1_7_ConditionTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 9:31
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.start();
    }
}

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {
        try {
            lock.lock();

            while (hasValue == true) {
                condition.await();
            }
            System.out.println("生产 XXX");
            hasValue=true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();

            while (hasValue == false) {
                condition.await();
            }
            System.out.println("消费 ***");
            hasValue=false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class MyThreadA extends Thread{
    private MyService service;

    MyThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.set();
        }
    }
}

class MyThreadB extends Thread{
    private MyService service;

    MyThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.get();
        }
    }
}
