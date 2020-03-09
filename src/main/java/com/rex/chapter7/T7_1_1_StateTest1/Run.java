package com.rex.chapter7.T7_1_1_StateTest1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 8:15
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程："+Thread.currentThread().getName());

        MyThread myThread = new MyThread();

        //线程刚刚创建时：为New状态
        System.out.println("main1 "+myThread.getState());
        Thread.sleep(2000);
        myThread.start(); //其余时间都是Runnable
        Thread.sleep(2000);

        //线程执行完后，为 Terminated
        System.out.println("main2 "+myThread.getState());

    }
}

class MyThread extends Thread{
    public MyThread(){
        //这里是由主线程调用的
        System.out.println(Thread.currentThread().getName() + " 构造 "+Thread.currentThread().getState());
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run "+Thread.currentThread().getState());
    }
}