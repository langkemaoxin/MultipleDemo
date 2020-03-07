package com.rex.chapter4.T4_1_4_UseConditionWaitNotifyOK;

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
 * await的时间为：1583543748652
 * 即将进入await
 * signal 获得了一把锁
 * Signal 时间为：1583543751652
 * signal 锁释放了
 * await 苏醒了
 * await锁 释放了
 *
 *
 * Object.wait() == Condition.await()
 * Object.wait(long timeout) == Condition.await(long time,TimeUnit unit)
 * Object.notify() == Condition.signal()
 * Object.notifyAll() == Condition.signalAll()
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.start();
        Thread.sleep(3000);

        myService.signal();
    }
}

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("await的时间为：" + System.currentTimeMillis());
            System.out.println("即将进入await");
            condition.await();
            System.out.println("await 苏醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("await锁 释放了");
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal 获得了一把锁");
            System.out.println("Signal 时间为：" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
            System.out.println("signal 锁释放了");
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
        myService.await();
    }
}