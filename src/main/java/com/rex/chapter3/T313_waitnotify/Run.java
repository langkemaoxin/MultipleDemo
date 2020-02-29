package com.rex.chapter3.T313_waitnotify;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/29 15:26
 * @Version 1.0
 *
 *
 * 运行结果：
 * 开始wait
 * 开始notify
 * 结束 notify
 * 结束 wait
 *
 * 本例子演示了，如何使用wait，notify
 * 一个线程把自己给锁住，然后等待别人来开锁
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread1 myThread1 = new MyThread1(lock);
        myThread1.start();

        Thread.sleep(1000);

        MyThread2 myThread2 = new MyThread2(lock);
        myThread2.start();
    }
}

class MyThread1 extends Thread {
    private Object lock;

    public MyThread1(Object object) {
        this.lock = object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始wait");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束 wait");
        }
    }
}


class MyThread2 extends Thread {
    private Object lock;

    public MyThread2(Object object) {
        this.lock = object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始notify");
            lock.notify();
            System.out.println("结束 notify");
        }
    }
}
