package com.rex.chapter6.T6_1_Singleton_7;

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
 * 使用内部类的机制
 * 内部类如果不去访问，则不会运行内部类的方法
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

     private static class MyObjectHanlder{
         private static MyObject myObject=new MyObject();
     }
     private MyObject(){
         System.out.println(" private MyObject()");
     }

     public static MyObject getInstance(){
         System.out.println("public static MyObject getInstance(){");
         return MyObjectHanlder.myObject;
     }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + MyObject.getInstance().hashCode());
    }
}

