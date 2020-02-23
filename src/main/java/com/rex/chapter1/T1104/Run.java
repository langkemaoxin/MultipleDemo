package com.rex.chapter1.T1104;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 13:55
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreaA threaA = new ThreaA();
        threaA.setPriority(Thread.NORM_PRIORITY-3);
        threaA.start();

        ThreaB threaB = new ThreaB();
        threaB.setPriority(Thread.NORM_PRIORITY+3);
        threaB.start();

        Thread.sleep(1000);

        threaA.stop();
        threaB.stop();

        System.out.println("a="+ threaA.getCount());
        System.out.println("b="+ threaB.getCount());

    }
}



class ThreaA extends Thread{
    private int count=0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true){
            count++;
        }
    }
}


class ThreaB extends Thread{
    private int count=0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true){
            count++;
        }
    }
}