package com.rex.chapter4.T4_1_11_LockMethodTest_HasWaiters;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 14:38
 * @Version 1.0
 * <p>
 *
 *
 * 演示效果：
 * true 线程数：10
 *
 * hasWaiters(Condition) 作用是查询是否有线程正在等待与此锁有关的Condition条件
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();

        Runnable runnable=new Runnable() {
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

        service.notity();

    }
}

class Service {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void serviceMethod1() {
        try {
            lock.lock();
            condition.await();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notity() {
        try {
            lock.lock();

            System.out.println(lock.hasWaiters(condition) +" 线程数："+lock.getWaitQueueLength(condition));

            condition.signal();

        } finally {
            lock.unlock();
        }
    }

}
