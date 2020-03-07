package com.rex.chapter4.T4_1_10_LockMethodTest1_getQueueLenth;

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
 * 进入了方法一
 * 当前正在等待的线程数量为：9
 *
 * getQueueLength() 的作用是返回正等待获取此锁定的线程估计数
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {

        //只有一份service，然后新建多个线程类，抢占式的执行里面的方法
        final Service service=new Service();

        //声明一个可以执行的类型，就是一个线程类
        Runnable runnable = new Runnable() {
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

        System.out.println("当前正在等待的线程数量为："+service.getQueueLength());
    }
}

class Service {
    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            System.out.println("进入了方法一");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getQueueLength(){
        return lock.getQueueLength();
    }
}


