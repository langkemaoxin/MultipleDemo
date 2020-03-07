package com.rex.chapter4.T4_1_9_Fair_noFair_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 11:47
 * @Version 1.0
 *
 *
 * Thread-0 运行了
 * Thread-4 运行了
 * Thread-5 运行了
 * Thread-3 运行了
 * Thread-1 运行了
 * Thread-2 运行了
 * Thread-9 运行了
 * Thread-8 运行了
 * Thread-7 运行了
 * 获得锁定Thread-0
 * Thread-6 运行了
 * 获得锁定Thread-4
 * 获得锁定Thread-5
 * 获得锁定Thread-3
 * 获得锁定Thread-1
 * 获得锁定Thread-2
 * 获得锁定Thread-9
 * 获得锁定Thread-8
 * 获得锁定Thread-7
 * 获得锁定Thread-6
 *
 *
 *
 *
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service(false);
        MyThread[] myThreadsArr = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreadsArr[i] = new MyThread(service);

        }

        for (int i = 0; i < 10; i++) {
            myThreadsArr[i].start();
        }
    }
}

class Service {
    private ReentrantLock lock;

    public Service(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void ServiceMethod() {
        try {
            lock.lock();
            System.out.println("获得锁定" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}

class MyThread extends Thread {
    private Service service;

    MyThread(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 运行了");
        service.ServiceMethod();
    }
}