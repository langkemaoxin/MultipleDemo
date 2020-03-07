package com.rex.chapter4.T4_1_6_MustUseMoreCondition_Ok;

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
 * signalALL_A 时间为：1583544530939
 * end awaitA
 *
 * 总结：
 * 如果想单独唤醒部分线程该怎么处理呢？使用多个Condition对象了。
 * 可以对线程进行分组，然后再唤醒指定组中的线程
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.start();

        ThreadB threadB = new ThreadB(myService);
        threadB.start();
        Thread.sleep(3000);

        myService.signalALL_A();

    }
}

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA");
            conditionA.await();
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
            conditionB.await();
            System.out.println("end awaitB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalALL_A() {
        try {
            lock.lock();
            System.out.println("signalALL_A 时间为：" + System.currentTimeMillis());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalALL_B() {
        try {
            lock.lock();
            System.out.println("signalALL_B 时间为：" + System.currentTimeMillis());
            conditionB.signalAll();
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