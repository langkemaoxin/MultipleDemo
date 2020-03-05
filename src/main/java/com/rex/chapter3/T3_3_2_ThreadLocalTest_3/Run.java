package com.rex.chapter3.T3_3_2_ThreadLocalTest_3;

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
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            //父线程里面的数据量
            System.out.println("main 的值"+Tools.tl.get());
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
}

class Tools {
    public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                //子线程里面的线程数据
                System.out.println("A "+Tools.tl.get());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
