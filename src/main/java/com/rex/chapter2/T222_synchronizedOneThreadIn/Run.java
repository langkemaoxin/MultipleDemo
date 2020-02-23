package com.rex.chapter2.T222_synchronizedOneThreadIn;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/23 10:19
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        MyThreadA myThreadA = new MyThreadA(service);
        myThreadA.setName("a");
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(service);
        myThreadB.setName("b");
        myThreadB.start();

    }
}

class MyThreadA extends Thread {
    private Service service;

    public MyThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class MyThreadB extends Thread {
    private Service service;

    public MyThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class Service {
    public void testMethod() {
        String name = Thread.currentThread().getName();

        synchronized (this) {

            System.out.println("ThreaName= " + name
                    + " run begin at " + System.currentTimeMillis());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("ThreaName= " + name
                    + " end begin at " + System.currentTimeMillis());
        }
    }
}
