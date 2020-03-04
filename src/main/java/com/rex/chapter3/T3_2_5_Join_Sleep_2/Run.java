package com.rex.chapter3.T3_2_5_Join_Sleep_2;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/4 22:21
 * @Version 1.0
 *
 *
 * 运行的结果为：
 * B run bgin timer=1583332514952
 * 打印了 bService timer=1583332515954
 * B run end timer=1583332519953
 *
 *
 * 本演示说明了，Join方法是释放锁的
 * 因为底层是用wait方法进行等待的，wait是释放锁的
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        threadA.start();

        Thread.sleep(1000);

        ThreadC threadC = new ThreadC(threadB);
        threadC.start();
    }
}

class ThreadA extends Thread {
    private ThreadB threadB;

    ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try {
            synchronized (threadB) {
                threadB.start();
                threadB.join();

                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String s = new String();
                    Math.random();
                }
            }
        } catch (InterruptedException e) {
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
        //这里是同步方法，必须是ThreadA释放了 ThreadB的锁之后才能进行
        threadB.bService();
    }
}


class ThreadB extends Thread {
    @Override
    public void run() {
        System.out.println("B run bgin timer=" + System.currentTimeMillis());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B run end timer=" + System.currentTimeMillis());
    }

    synchronized public void bService() {
        System.out.println("打印了 bService timer=" + System.currentTimeMillis());
    }
}





















