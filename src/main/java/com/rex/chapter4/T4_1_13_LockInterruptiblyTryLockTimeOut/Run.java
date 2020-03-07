package com.rex.chapter4.T4_1_13_LockInterruptiblyTryLockTimeOut;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 16:38
 * @Version 1.0
 * <p>
 *
 * 尝试获取锁指定秒数 lock.tryLock(3, TimeUnit.SECONDS)
 *
 * 演示效果：
 *
 * 运行A1583570477024
 *  A获得锁的时间1583570477025
 * 运行B1583570477525
 *  B没有获得锁
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("运行" + Thread.currentThread().getName() + System.currentTimeMillis());
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


    }
}

class Service {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void serviceMethod1() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println(" " + Thread.currentThread().getName() + "获得锁的时间" + System.currentTimeMillis());
                Thread.sleep(10000);
            } else {
                System.out.println(" " + Thread.currentThread().getName() + "没有获得锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
