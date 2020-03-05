package com.rex.chapter3.T3_3_2_ThreadLocalTest;

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
 * Main get valueMain 1
 * ThreadB get Value=ThreadB1
 * ThreadA get Value=ThreadA1
 * ThreadB get Value=ThreadB2
 * Main get valueMain 2
 * ThreadA get Value=ThreadA2
 *
 *
 * 演示说明了
 * public static ThreadLocal tl = new ThreadLocal();
 *
 * 只要开启了一个线程，线程都拥有自己独立的数据区域
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();

        for (int i = 0; i < 100; i++) {
            Tools.tl.set("Main "+(i+1));
            System.out.println("Main get value"+Tools.tl.get());
            Thread.sleep(200);
        }
    }
}

class Tools {
    public static ThreadLocal tl = new ThreadLocal();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("ThreadA" + (i + 1));

                System.out.println("ThreadA get Value=" + Tools.tl.get());

                Thread.sleep(200);
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
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("ThreadB" + (i + 1));

                System.out.println("ThreadB get Value=" + Tools.tl.get());

                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


