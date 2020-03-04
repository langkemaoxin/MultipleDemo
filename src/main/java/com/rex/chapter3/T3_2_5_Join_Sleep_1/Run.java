package com.rex.chapter3.T3_2_5_Join_Sleep_1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/4 22:21
 * @Version 1.0
 *
 *
 * 演示了 Thread.Sleep()是不释放锁的
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
                //不释放锁,所以这里一直持有 threadB的锁，时间长达6秒
                Thread.sleep(6000);
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





















