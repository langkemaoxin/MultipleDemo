package com.rex.chapter4.T4_1_3_UseConditionWaitNotifyError;

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
 * 不能单独的使用 condition.await(); 得先获得lock锁
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
            //这里报错了
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
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