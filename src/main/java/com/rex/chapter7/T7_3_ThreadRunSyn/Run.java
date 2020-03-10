package com.rex.chapter7.T7_3_ThreadRunSyn;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 7:45
 * @Version 1.0
 *
 * 巧妙使用 volatile,lock.wait,lock.notifyAll(), 实现线程顺序执行
 */
public class Run {
    public static void main(String[] args) {
        Object o = new Object();
        MyThread a = new MyThread(o, "A", 1);
        MyThread b = new MyThread(o, "B", 2);
        MyThread c = new MyThread(o, "C", 0);
        a.start();
        b.start();
        c.start();
    }
}

class MyThread extends Thread {
    private Object lock;
    private String threadName;

    //每个人自己的位置
    private int showNumPosition;

    //运行的次数
    private int printCount = 0;
    volatile private static int addNumber = 1;

    public MyThread(Object lock, String threadName, int showNumPosition) {
        this.lock = lock;
        this.threadName = threadName;
        this.showNumPosition = showNumPosition;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (true) {

                    //计算出当前应当执行任务的ID
                    if (addNumber % 3 == showNumPosition) {

                        //执行任务
                        System.out.println("ThreadName="
                                + Thread.currentThread().getName()
                                + " runCount=" + addNumber + " " + threadName);

                        //通知其他人，事情已经做完
                        lock.notifyAll();

                        //
                        addNumber++;
                        printCount++;
                        if (printCount == 3) {
                            break;
                        }
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}