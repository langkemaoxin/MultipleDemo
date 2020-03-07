package com.rex.chapter4.T4_1_13_LockInterruptiblyTest2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 14:38
 * @Version 1.0
 * <p>
 * <p>
 * <p>
 *
 *  lock.lockInterruptibly();
 *  当前线程中断时，报异常
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod1();
            }
        };

        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();

        Thread.sleep(500);

        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();

        threadB.interrupt();
        System.out.println("main end");

    }
}

class Service {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void serviceMethod1() {
        try {
            lock.lockInterruptibly();

            System.out.println("lock begin " + Thread.currentThread().getName());

            for (int i = 0; i < Integer.MAX_VALUE / 100; i++) {
                Math.random();
                Math.random();
                Math.random();
            }

            System.out.println("lock end " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
