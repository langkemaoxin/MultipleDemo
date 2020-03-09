package com.rex.chapter7.T7_2_6_GroupInnerStop;


/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 23:43
 * @Version 1.0
 *
 * 1. 新建一个线程组
 * 2. 往线程组里面添加线程
 * 3. 只要线程组发出停止命令，则所有线程都停止了
 */
public class Run {
    public static void main(String[] args) {
        try {

            ThreadGroup group = new ThreadGroup("我的线程组");
            for (int i = 0; i < 5; i++) {
                //线程组中添加线程
                MyThread myThread = new MyThread(group, "线程" + (i + 1));
                myThread.start();
            }

            Thread.sleep(5000);

            group.interrupt();

            System.out.println("调用了interrupt方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("停止了。。。");
        }

    }
}


class MyThread extends Thread {
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName=" + Thread.currentThread().getName() + "准备开始死循环了：");
        while (!this.isInterrupted()) {

        }

        System.out.println("ThreadName=" + Thread.currentThread().getName() + "结束了");
    }
}