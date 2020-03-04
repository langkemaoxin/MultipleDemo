package com.rex.chapter3.T3_2_3_JoinException;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/4 8:38
 * @Version 1.0
 *
 *
 * 此案例演示了当一个线程去Join等待另一个线程结束时，
 * 此时线程若执行 interrupt 则会引发异常
 */
public class Run {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        threadB.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadC threadC = new ThreadC(threadB);
        threadC.start();
    }
}

class ThreadA extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newString = new String();
            Math.random();
        }
    }
}


class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            ThreadA threadA = new ThreadA();
            threadA.start();
            threadA.join();

            System.out.println("线程B在runend处打印了");
        } catch (InterruptedException e) {
            System.out.println("线程B在异常处打印了");
            e.printStackTrace();
        }


    }
}

class ThreadC extends Thread {
    private ThreadB threadB;

    ThreadC(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.interrupt();
    }
}