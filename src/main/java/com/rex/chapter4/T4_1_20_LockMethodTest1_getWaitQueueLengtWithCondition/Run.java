package com.rex.chapter4.T4_1_20_LockMethodTest1_getWaitQueueLengtWithCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 14:38
 * @Version 1.0
 *
 *
 *
 * 演示效果：
 * 当前等待的线程数：10
 *
 * getWaitQueueLength() 的作用是返回等待与此锁定相关的给定条件Condition的线程估计数
 * 
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {


        final Service service=new Service();


        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                service.serviceMethod1();
            }
        };

        Thread[] threads=new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i]=new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        Thread.sleep(200);

        service.notityMethod();
    }
}

class Service {
    private ReentrantLock lock = new ReentrantLock();

    //锁分组一
    private Condition condition1=lock.newCondition();

    //锁分组二
    private Condition condition2=lock.newCondition();

    public void serviceMethod1() {
        try {
            lock.lock();
            condition1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notityMethod(){
        try{
            lock.lock();
            System.out.println("当前等待的线程数："+lock.getWaitQueueLength(condition1));
        }finally {
            lock.unlock();
        }
    }


}


