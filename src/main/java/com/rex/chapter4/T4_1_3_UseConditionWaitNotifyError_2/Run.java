package com.rex.chapter4.T4_1_3_UseConditionWaitNotifyError_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 8:54
 * @Version 1.0
 *
 * 这里只会打印A出来，然后就  condition.await(); 意思为释放锁了，
 * 所以当前执行任务的线程进入了 等待 WAITING状态
 *
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.start();
    }
}

class MyService{
    private Lock lock= new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void await(){
        try {
            lock.lock();
            System.out.println("A");

            condition.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("锁 释放了");
        }
    }
}

class ThreadA extends Thread{
    private MyService myService;

    ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.await();
    }
}