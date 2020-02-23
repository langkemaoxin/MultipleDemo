package com.rex.chapter1.T13;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/19 22:53
 * @Version 1.0
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            if(this.interrupted()){
                System.out.println("已经停止了哈");
                break;
            }
            System.out.println("i="+(i+1));
        }

        System.out.println("我还能跑哦");
    }
}




class Run{
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1);
        myThread.interrupt();

    }
}


class Run2{
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().interrupt();
        System.out.println("是否停止1?="+Thread.interrupted());
        System.out.println("是否停止2?="+Thread.interrupted());
        System.out.println("end!");
    }
}


class Run3{
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(100);
        myThread.interrupt();
        //Thread.currentThread().interrupt();
        System.out.println("是否停止1?="+myThread.isInterrupted());
        System.out.println("是否停止2?="+myThread.isInterrupted());
    }
}
