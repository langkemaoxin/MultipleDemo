package com.rex.chapter7.T7_6_1_ThreadGroup1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 22:31
 * @Version 1.0
 *
 * 线程组中，如果有一个线程挂了，其他的不受影响
 */
public class Run {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("我的线程组");
        MyThread[] myThreads = new MyThread[2];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i]=new MyThread(group,"线程"+(i+1),"1");
            myThreads[i].start();
        }

        MyThread myThread = new MyThread(group, "报错线程", "a");
        myThread.start();

    }
}

class MyThread extends Thread{
    private String num;
    public MyThread(ThreadGroup group,String name,String num){
        super(group,name);
        this.num=num;
    }

    @Override
    public void run() {
        int numInt = Integer.parseInt(num);
        while (true){
            System.out.println("死循环中"+Thread.currentThread().getName());
        }
    }
}
