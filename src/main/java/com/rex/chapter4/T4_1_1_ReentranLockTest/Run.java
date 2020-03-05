package com.rex.chapter4.T4_1_1_ReentranLockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/5 22:50
 * @Version 1.0
 *
 *
 *
 *  运行结果：
 *  Thread-0 1
 * Thread-0 2
 * Thread-0 3
 * Thread-0 4
 * Thread-0 5
 * Thread-1 1
 * Thread-1 2
 * Thread-1 3
 * Thread-1 4
 * Thread-1 5
 * Thread-2 1
 * Thread-2 2
 * Thread-2 3
 * Thread-2 4
 * Thread-2 5
 * Thread-4 1
 * Thread-4 2
 * Thread-4 3
 * Thread-4 4
 * Thread-4 5
 * Thread-3 1
 * Thread-3 2
 * Thread-3 3
 * Thread-3 4
 * Thread-3 5
 *
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread1 = new MyThread(myService);
        MyThread myThread2 = new MyThread(myService);
        MyThread myThread3 = new MyThread(myService);
        MyThread myThread4 = new MyThread(myService);
        MyThread myThread5 = new MyThread(myService);

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();

    }
}

class MyService{
    private Lock lock=new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" "+(i+1));
        }
        lock.unlock();
    }
}

class MyThread extends Thread{
    private MyService service;

    MyThread(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}