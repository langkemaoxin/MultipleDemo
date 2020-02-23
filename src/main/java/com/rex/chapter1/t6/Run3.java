package com.rex.chapter1.t6;

public class Run3{

    public static void main(String[] args) {
        CountOoperate countOoperate = new CountOoperate();
        Thread thread = new Thread(countOoperate);
        thread.setName("AAAA");
        thread.start();
    }
}

class CountOoperate extends Thread{
    public CountOoperate(){
        System.out.println("CoutOperate---begin");
        System.out.println("Thread.currentThread.().getName()="+Thread.currentThread().getName());
        System.out.println("this.getName()="+this.getName());
        System.out.println("CountOpetate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread.().getName()="+Thread.currentThread().getName());
        System.out.println("this.getName()="+this.getName());
        System.out.println("run---end");

    }
}
