package com.rex.chapter6.T6_5_Singleton_8;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 22:49
 * @Version 1.0
 *
 * 使用静态代码块这个特性实现单例模式设计
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

    private static MyObject myObject=null;

    public MyObject() {
    }
    static {
        myObject=new MyObject();
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

