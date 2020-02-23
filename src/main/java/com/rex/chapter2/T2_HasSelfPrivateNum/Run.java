package com.rex.chapter2.T2_HasSelfPrivateNum;

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
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        MyThreaA myThreaA = new MyThreaA(hasSelfPrivateNum);
        myThreaA.start();

        MyThreaB myThreaB = new MyThreaB(hasSelfPrivateNum);
        myThreaB.start();

    }
}


class HasSelfPrivateNum {
    private int num = 0;

    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);

            } else {
                num = 200;
                System.out.println("b set over");
            }

            System.out.println("num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreaA extends Thread {

    private HasSelfPrivateNum numRef;

    public MyThreaA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}

class MyThreaB extends Thread {

    private HasSelfPrivateNum numRef;

    public MyThreaB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}