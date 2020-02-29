package com.rex.chapter3.T313_wait_notify_size5;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/29 15:34
 * @Version 1.0
 *
 *
 * 运行结果：
 *
 * wait begin
 * 添加了1个元素
 * 添加了2个元素
 * 添加了3个元素
 * 添加了4个元素
 * notify已经发出了
 * 添加了5个元素
 * 添加了6个元素
 * 添加了7个元素
 * 添加了8个元素
 * 添加了9个元素
 * 添加了10个元素
 * wait end
 *
 * 说明一点：当对象发出notify的时候，这个时候不会马上释放锁，而是标记为一个状态，
 * 必须出了同步区域，其他线程中才能继续执行
 *
 * wait()方法可以使调用该方法的线程释放共享资源的锁，然后从运行状态退出，进入等待队列，直到再次被唤醒
 *
 * notify()方法可以随机唤醒等待队列中等待同一共享资源的“一个” 线程，并使该线程退出等待队列，进入可运行状态，
 * 也就是 notify方法仅通知一个线程
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.start();

        ThreadA threadA1 = new ThreadA(lock);
        threadA1.start();

        ThreadA threadA2 = new ThreadA(lock);
        threadA2.start();

        ThreadA threadA3 = new ThreadA(lock);
        threadA3.start();


        Thread.sleep(3000);

        ThreadB threadB = new ThreadB(lock);
        threadB.start();

    }
}

class MyList {
    private static List list = new ArrayList<>();

    public static void add() {
        list.add("any");
    }

    public static int size() {
        return list.size();
    }
}

class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    /**
     * 在列表的数量不等于5之前，进入等待状态
     */
    @Override
    public void run() {
        synchronized (lock) {
            if (MyList.size() != 5) {
                System.out.println("wait begin");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end");
            }
        }
    }
}

class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                MyList.add();
                if (MyList.size() == 5) {
                    lock.notifyAll();
                    System.out.println("notify已经发出了");
                }
                System.out.println("添加了" + (i + 1) + "个元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}