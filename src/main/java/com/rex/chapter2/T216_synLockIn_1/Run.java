package com.rex.chapter2.T216_synLockIn_1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/23 9:58
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends  Thread{
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}


class Service{
    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }

    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }
}