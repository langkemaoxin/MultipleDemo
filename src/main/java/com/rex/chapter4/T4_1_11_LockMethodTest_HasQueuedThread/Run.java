package com.rex.chapter4.T4_1_11_LockMethodTest_HasQueuedThread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 14:38
 * @Version 1.0
 * <p>
 *
*         //查询线程A是否等待获取此锁定
*         System.out.println(service.lock.hasQueuedThread(threadA));
*
*         //查询线程B是否等待获取此锁
*         System.out.println(service.lock.hasQueuedThread(threadB));
*
*         //查询是否有线程等待此锁
*         System.out.println(service.lock.hasQueuedThreads());
*
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

        Thread threadA=new Thread(runnable);
        threadA.start();
        Thread.sleep(100);
        Thread threadB=new Thread(runnable);
        threadB.start();

        Thread.sleep(2000);


        //查询线程A是否等待获取此锁定
        System.out.println(service.lock.hasQueuedThread(threadA));

        //查询线程B是否等待获取此锁
        System.out.println(service.lock.hasQueuedThread(threadB));

        //查询是否有线程等待此锁
        System.out.println(service.lock.hasQueuedThreads());


    }
}

class Service {
    public ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
