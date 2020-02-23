package com.rex.chapter1.T182_suspend_resume_LockStop;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 11:14
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.suspend();
        System.out.println("main end");
    }
}

 class MyThread extends Thread{
    private long i=0;

     @Override
     public void run() {
         while (true){
             i++;
             System.out.println(i);
         }
     }
 }