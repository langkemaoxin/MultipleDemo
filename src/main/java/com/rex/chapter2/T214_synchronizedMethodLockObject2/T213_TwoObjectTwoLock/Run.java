package com.rex.chapter2.T214_synchronizedMethodLockObject2.T213_TwoObjectTwoLock;

/**
 * 使用线程的方式：需要传入外部对象，然后开启两个线程，同时对这一个
 * 对象进行处理。开启一个线程，调用这个对象的方法
 */

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 16:25
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        MyThreadA myThreadA = new MyThreadA(myObject);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myObject);
        myThreadB.start();


    }
}

class MyObject {
    synchronized public void methodA() {
        System.out.println("begin MethodA threadName" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end " + Thread.currentThread().getName());

    }

    synchronized public void methodB() {
        System.out.println("begin methodB threadName" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end " + Thread.currentThread().getName());

    }
}

class MyThreadA extends Thread{
    private MyObject object;

    public MyThreadA(MyObject object) {
        super();
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}


class MyThreadB extends Thread{
    private MyObject object;

    public MyThreadB(MyObject object) {
        super();
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodB();
    }
}
