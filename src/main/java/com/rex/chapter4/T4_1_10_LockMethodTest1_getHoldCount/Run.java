package com.rex.chapter4.T4_1_10_LockMethodTest1_getHoldCount;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 14:38
 * @Version 1.0
 *
 * getHoldCount() 的作用是查询当前线程保持此锁定的个数，也就是调用lock方法的次数
 *
 * 当前线程持有多少个线程数
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        service.serviceMethod1();
        /**
         * 在同一个方法中，调用了lock对象两次，所以这里的cout=2
         * 也就是重入的次数为2
         */

        System.out.println("----------------------");

        service.serviceMethod1();
    }
}

class Service{
    private ReentrantLock lock=new ReentrantLock();
    public void serviceMethod1(){
        try{
            lock.lock();
            System.out.println("Method1 Count="+lock.getHoldCount());
            serviceMethod2();
        }finally {
            lock.unlock();
        }
    }

    public void serviceMethod2(){
        try{
            lock.lock();
            System.out.println("Method2 Count="+lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }
}
