package com.rex.chapter2.T217_throwExceptionNoLock;

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

//        Thread.sleep(100);

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
    synchronized public void testMethod() {
        String name = Thread.currentThread().getName();
        if (name.equals("a")) {

            System.out.println("ThreaName= " + name
                    + " run begin at " + System.currentTimeMillis());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("ThreaName=" + name
                    + " run Exception at " + System.currentTimeMillis());
            Integer.parseInt("a");

        } else {
            System.out.println("ThreaName=" + name
                    + " run begin at " + System.currentTimeMillis());
        }
    }
}
