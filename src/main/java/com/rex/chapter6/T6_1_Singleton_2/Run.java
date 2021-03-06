package com.rex.chapter6.T6_1_Singleton_2;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 21:49
 * @Version 1.0
 *
 * 懒汉式 构造的时候不安全
 *
 * 进入构造案例Thread-0
 * Thread-0 759516930
 * Thread-1 759516930
 * Thread-2 759516930
 *
 * 解决方式：加上一个同步方法锁
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

class MyObject{

    //懒汉式
    private static MyObject myObject;

    public MyObject() {
    }

    public synchronized static MyObject getInstance(){

       if(myObject==null){
           System.out.println("进入构造案例"+Thread.currentThread().getName());
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           myObject=new MyObject();
       }

       return myObject;
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " "+ MyObject.getInstance().hashCode());
    }
}

