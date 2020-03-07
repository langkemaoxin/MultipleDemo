package com.rex.chapter4.T4_1_16_OrderCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description 使用Condition实现顺序执行
 * @Author GY.C
 * @Date 2020/3/7 22:11
 * @Version 1.0
 *
 * 使用Condition对象可以对线程执行的业务进行排序规划
 *
 * 演示效果：
 * ThreadA 1
 * ThreadA 2
 * ThreadA 3
 * ThreadB 1
 * ThreadB 2
 * ThreadB 3
 *
 *
 */
public class Run {
    volatile private static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    final private static Condition conditionA = lock.newCondition();
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 1) {
                        conditionA.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadA "+(i+1));
                    }
                    nextPrintWho=2;
                    conditionB.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadB "+(i+1));
                    }
                    nextPrintWho=3;
                    conditionC.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        conditionC.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadC "+(i+1));
                    }
                    nextPrintWho=1;
                    conditionA.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };


        Thread[] aArray=new Thread[5];
        Thread[] bArray=new Thread[5];
        Thread[] cArray=new Thread[5];

        for (int i = 0; i < 5; i++) {
            aArray[i]=new Thread(threadA);
            bArray[i]=new Thread(threadB);
            cArray[i]=new Thread(threadC);

            aArray[i].start();
            bArray[i].start();
            cArray[i].start();
        }
    }
}

class F {
    volatile public static int nextPrintWho = 1;
}
