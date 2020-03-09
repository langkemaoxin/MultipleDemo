package com.rex.chapter7.T7_2_2_GroupAddThread;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 22:30
 * @Version 1.0
 *
 *    创建一个线程组
 *    ThreadGroup group = new ThreadGroup("线程组");
 *
 *    把一个线程放到线程组里面
 *    Thread athread = new Thread(group, threadA);
 *
 */
public class Run {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadGroup group = new ThreadGroup("线程组");
        Thread athread = new Thread(group, threadA);
        Thread bthread = new Thread(group, threadB);

        athread.start();
        bthread.start();

        System.out.println("线程数为："+group.activeCount());
        System.out.println("组名为："+group.getName());

    }
}

class ThreadA extends Thread {
    @Override
    public void run() {

        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("name="+Thread.currentThread().getName());
                Thread.sleep(3000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("name="+Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
