package com.rex.chapter2.T2210_StringAttribute;


/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/28 23:51
 * @Version 1.0
 *
 *
 * 此方法演示的是 因为字符常驻的特性，导致在锁两个字符对象的时候，
 * 如果对象是同样的字符，那就会导致产生死锁现象
 */
public class Run {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        threadB.start();

    }
}

class Service{
    public static void print(String stringParam){
        try {
            synchronized (stringParam){
                while (true){
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA extends Thread{

    @Override
    public void run() {
        Service.print("AA");
    }
}


class ThreadB extends Thread{

    @Override
    public void run() {
        Service.print("AA");
    }
}
