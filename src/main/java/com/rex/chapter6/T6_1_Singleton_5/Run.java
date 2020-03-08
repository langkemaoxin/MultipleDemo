package com.rex.chapter6.T6_1_Singleton_5;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 21:49
 * @Version 1.0
 * <p>
 * 懒汉式 构造的时候不安全
 * <p>
 * 进入构造案例Thread-0
 * Thread-0 759516930
 * Thread-1 759516930
 * Thread-2 759516930
 * <p>
 * <p>
 *
 * 使用 DCL双检查锁机制 [Double Check locking]
 *
 * 为什么DCL能解决懒汉式的单例问题？
 *
 *  1 每个线程都可以进来
 *  2 倘若是直接有数据了，则直接返回
 *  3 倘若没有数据，则锁住一小部分
 *
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}

class MyObject {

    //懒汉式
    private static MyObject myObject;

    public MyObject() {
    }

    public static MyObject getInstance() {
        try {
            if (myObject == null) {
                System.out.println("进入构造案例" + Thread.currentThread().getName());
                Thread.sleep(3000);
                synchronized (MyObject.class){
                    if(myObject==null){
                        myObject = new MyObject();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + MyObject.getInstance().hashCode());
    }
}

