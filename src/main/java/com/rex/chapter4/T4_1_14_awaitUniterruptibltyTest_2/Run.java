package com.rex.chapter4.T4_1_14_awaitUniterruptibltyTest_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 24:44
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Mythread mythread = new Mythread(service);
        mythread.start();
        Thread.sleep(3000);
        mythread.interrupt();
    }
}

class Service {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod() {
        try {

            lock.lock();
            System.out.println("wait begin");
            condition.awaitUninterruptibly();
            System.out.println("wait end");

        } finally {
            lock.unlock();
        }
    }
}

class Mythread extends Thread {
    private Service service;

    Mythread(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}




