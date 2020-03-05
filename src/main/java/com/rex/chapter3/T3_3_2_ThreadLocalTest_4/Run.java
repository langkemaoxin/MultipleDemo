package com.rex.chapter3.T3_3_2_ThreadLocalTest_4;

import java.util.Date;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/5 22:00
 * @Version 1.0
 *
 *
 * 使用InheritableThreadLocal 类可以让子线程从父线程中取得值
 *
 * 重写childValue方法，可以让子线程增加别的信息
 *  @Override
 *     protected Object childValue(Object parentValue) {
 *         return parentValue+"我叫子线程";
 *     }
 *
 *
 * 演示效果
 * main 的值1583419450916
 * main 的值1583419450916
 * A 1583419450916 我增加了一些东西
 * A 1583419450916 我增加了一些东西
 * B 1583419450916 我增加了一些东西  我增加了一些东西
 * B 1583419450916 我增加了一些东西  我增加了一些东西
 *
 * 可以看到，每次开辟一个子线程，都能继承上一个层级的私有数据
 *
 * 代代相传
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            //父线程里面的数据量
            System.out.println("main 的值"+ Tools.tl.get());
            Thread.sleep(200);
        }

        Thread.sleep(2000);

        ThreadA threadA = new ThreadA();
        threadA.start();
    }
}


class InheritableThreadLocalExt extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue+" 我增加了一些东西 ";
    }
}

class Tools {
    public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                //子线程里面的线程数据
                System.out.println("A "+ Tools.tl.get());

                Thread.sleep(100);
            }
            new ThreadB().start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                //子线程里面的线程数据
                System.out.println("B "+ Tools.tl.get());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

