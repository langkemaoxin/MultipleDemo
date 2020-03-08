package com.rex.chapter6.T6_1_Singleton_1;

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
 * 进入构造案例Thread-1
 * 进入构造案例Thread-2
 * Thread-2 451883120
 * Thread-1 244606942
 * Thread-0 451883120
 *
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

    public static MyObject getInstance(){

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
        System.out.println(Thread.currentThread().getName()+ " "+MyObject.getInstance().hashCode());
    }
}

