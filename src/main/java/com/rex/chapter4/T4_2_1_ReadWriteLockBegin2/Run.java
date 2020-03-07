package com.rex.chapter4.T4_2_1_ReadWriteLockBegin2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName Run
 * @Description 类 ReentrantReadWriteLock：读写互斥
 * @Author GY.C
 * @Date 2020/3/7 22:24
 * @Version 1.0
 *
 * 演示效果:
 * 获得锁A1583591527375
 * 获得锁B1583591529376
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MythreadA mythreadA = new MythreadA(service);
        mythreadA.setName("A");

        MythreadB mythreadB = new MythreadB(service);
        mythreadB.setName("B");

        mythreadA.start();
        mythreadB.start();

    }
}

class Service {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void write(){
        try {
            lock.writeLock().lock();
            System.out.println("获得锁"+Thread.currentThread().getName()+System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}


class MythreadA extends Thread{
    private Service service;

    MythreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}

class MythreadB extends Thread{
    private Service service;

    MythreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}