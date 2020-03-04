package com.rex.chapter3.T3_2_6_JoinMoreTest;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/4 22:42
 * @Version 1.0
 *
 *
 * 第一种方式：
 * 1) b.join(2000)方法抢到B锁，然后将B锁进行释放
 * 2) ThreadA抢到锁，打印ThreadA Begin 并且Sleep(5000);
 * 3) ThreadA打印 ThreadA End,并释放锁
 * 4) 此时Join(2000) 和 ThreadB争抢锁，而join(2000)再次抢到锁，发现时间已过，释放锁后打印Main end;
 * 5) ThreaB 抢到锁打印 ThreadB Begin
 * 6) Threab 打印 ThreadB end
 *
 *
 * 第二种方式：
 * 1) b.join(2000)方法抢到B锁，然后将B锁进行释放
 * 2) ThreadA抢到锁，打印ThreadA Begin 并且Sleep(5000);
 * 3) ThreadA打印 ThreadA End,并释放锁
 * 4) 此时Join(2000) 和 ThreadB争抢锁，ThreadB抢到了锁
 * 5) ThreaB 抢到锁打印 ThreadB Begin
 * 6) Threab 打印 ThreadB end
 * 7) 打印Main end
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        threadA.start();
        threadB.start();

        threadB.join(2000);

        System.out.println("Main");
    }
}


class RunFirst {
    public static void main(String[] args) throws InterruptedException {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        threadA.start();
        threadB.start();


        System.out.println("Main");
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

                System.out.println("A run bgin timer=" + System.currentTimeMillis());
                Thread.sleep(5000);

                System.out.println("A run end timer=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {
    @Override
    synchronized public void run() {
        try {
            System.out.println("B run bgin timer=" + System.currentTimeMillis());

            Thread.sleep(5000);

            System.out.println("B run end timer=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

