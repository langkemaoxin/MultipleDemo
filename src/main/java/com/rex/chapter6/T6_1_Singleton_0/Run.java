package com.rex.chapter6.T6_1_Singleton_0;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 21:49
 * @Version 1.0
 *
 * 立即加载 / 饿汉式
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

    //饿汉式
    private static MyObject myObject=new MyObject();

    public MyObject() {
    }

    public static MyObject getInstance(){
        return myObject;
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}

