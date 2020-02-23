package com.rex.chapter1.T3;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 22:05
 * @Version 1.0
 */
public class MyThread extends Thread {
    private int count=5;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count>0){
            count--;
            System.out.println("由"+this.currentThread().getName()+"计算,count="+count);
        }
    }
}

class test{
    public static void main(String[] args) {
        MyThread A = new MyThread("A");
        MyThread B = new MyThread("B");
        MyThread C = new MyThread("C");

        A.start();
        B.start();
        C.start();


    }
}