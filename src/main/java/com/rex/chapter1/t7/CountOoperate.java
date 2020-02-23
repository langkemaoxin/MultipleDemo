package com.rex.chapter1.t7;

class CountOoperate extends Thread {
    public CountOoperate() {
        System.out.println("CoutOperate---begin");
        System.out.println("Thread.currentThread.().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread.().isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("CountOpetate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread.().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread.().isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("run---end");

    }
}

class Run3 {

    public static void main(String[] args) {
        CountOoperate countOoperate = new CountOoperate();
        Thread thread = new Thread(countOoperate);
        System.out.println("main begin isAlive="+thread.isAlive());
        thread.setName("AAAA");
        thread.start();
        System.out.println("main begin isAlive="+thread.isAlive());
    }
}