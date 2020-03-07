package com.rex.chapter4.T4_1_14_awaitUniterruptibltyTest_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 24:44
 * @Version 1.0
 *
 * 程序运行后出现异常
 *
 * 1. 程序运行到 condition.await();
 * 2.  mythread.interrupt(); 程序终止了
 * 3. 就会产生了InterruptedException
 *
 *
 * 演示结果：
 * wait begin
 * java.lang.InterruptedException
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.reportInterruptAfterWait(AbstractQueuedSynchronizer.java:2014)
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2048)
 * 	at com.rex.chapter4.T4_1_14_awaitUniterruptibltyTest_1.Service.testMethod(Run.java:33)
 * 	at com.rex.chapter4.T4_1_14_awaitUniterruptibltyTest_1.Mythread.run(Run.java:54)
 * catch
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Mythread mythread = new Mythread(service);
        mythread.start();
        Thread.sleep(3000);
        mythread.interrupt();
    }
}

class Service {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod() {
        try {

            lock.lock();
            System.out.println("wait begin");
            condition.await();
            System.out.println("wait end");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("catch");
        }finally {
            lock.unlock();
        }
    }
}

class Mythread extends Thread{
    private Service service;

    Mythread(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}




