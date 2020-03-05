package com.rex.chapter3.T3_3_2_ThreadLocalTest_1;

import java.util.Date;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/5 22:00
 * @Version 1.0
 *
 *
 *  验证线程变量的隔离性
 *
 * 演示结果
 *
 * A 1583417512613
 * A 1583417512613
 * A 1583417512613
 * A 1583417512613
 * A 1583417512613
 * A 1583417512613
 * A 1583417512613
 *
 * 演示说明了
 * public static ThreadLocal<Date> tl = new ThreadLocal();
 *
 * 只要开启了一个线程，线程都拥有自己独立的数据区域
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();

        Thread.sleep(2000);

        ThreadB threadB = new ThreadB();
        threadB.start();
    }
}

class Tools {
    public static ThreadLocal<Date> tl = new ThreadLocal();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if(Tools.tl.get()==null){
                    Tools.tl.set(new Date());
                }

                System.out.println("A "+Tools.tl.get().getTime());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if(Tools.tl.get()==null){
                    Tools.tl.set(new Date());
                }

                System.out.println("B "+Tools.tl.get().getTime());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


