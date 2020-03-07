package com.rex.chapter4.T4_1_15_AwaitUntilTest;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 21:59
 * @Version 1.0
 *
 * 阻塞线程10秒钟，然后自动唤醒
 * condition.awaitUntil(calendarRef.getTime());
 *
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MythreadA mythreadA = new MythreadA(service);
        mythreadA.start();
    }
}

class Service {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            Calendar calendarRef = Calendar.getInstance();
            calendarRef.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("wait begin time=" + System.currentTimeMillis());

            //等待到一个绝对时间
            condition.awaitUntil(calendarRef.getTime());
            System.out.println("wait end time=" + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {

            Calendar calendarRef = Calendar.getInstance();

            calendarRef.add(Calendar.SECOND, 10);

            lock.lock();
            System.out.println("notify begin time=" + System.currentTimeMillis());
            //等待到一个绝对时间
            condition.signalAll();
            System.out.println("notify end time=" + System.currentTimeMillis());
        } finally {
            lock.unlock();
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
        service.waitMethod();
    }
}

class MythreadB extends Thread{
    private Service service;

    MythreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.notifyMethod();
    }
}