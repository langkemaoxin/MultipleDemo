package com.rex.chapter2.T216_synLockIn_2;

import sun.awt.windows.ThemeReader;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/23 10:08
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}


class MyThread extends Thread{
    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}

class Main{
    public  int i=10;
    synchronized public void operateIMainMethod(){
        try {
            i--;
            System.out.println("main i="+i);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Sub extends Main{
    synchronized public void operateISubMethod(){
        while (i>0){
            i--;
            System.out.println("Sub i="+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.operateIMainMethod();
        }
    }
}

