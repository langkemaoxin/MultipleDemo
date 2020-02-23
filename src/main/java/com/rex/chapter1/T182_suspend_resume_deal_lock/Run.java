package com.rex.chapter1.T182_suspend_resume_deal_lock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 11:14
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        try {

            final SynchronizedObject synchronizedObject = new SynchronizedObject();
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    synchronizedObject.printString();
                }
            };

            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    System.out.println("thread2启动了，但进不了pringString()，只打印一个begin");
                    System.out.println("因为pringString方法被a线程锁定并且永远suspend暂停了");
                    synchronizedObject.printString();
                }
            };

            thread2.start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SynchronizedObject {
    synchronized public void printString() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a 线程永远 suspend!了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}
