package com.rex.chapter2.T224;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/23 15:37
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();

    }
}

class Task {

    public void doLongTimeTask() {
        for (int i = 0; i <100; i++) {
            System.out.println("nosynchronized threadName="+Thread.currentThread().getName()+" i="+(i+1));
        }

        System.out.println("");

        synchronized (this){
            for (int i = 0; i <100; i++) {
                System.out.println("synchronized threadName="+Thread.currentThread().getName()+" i="+(i+1));
            }
        }
    }
}



class MyThread1 extends Thread {
    private Task task;

    MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}


class MyThread2 extends Thread {
    private Task task;

    MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}


/**
 * 演示说明：
 * 同步方法是阻塞的，一个线程执行完才能下一个线程执行。非常耗时
 * <p>
 * 演示结果：
 * begin Task
 * 长时间处理任务后从远程返回的值=Thread-0
 * 长时间处理任务后从远程返回的值=Thread-0
 * end task!
 * begin Task
 * 长时间处理任务后从远程返回的值=Thread-1
 * 长时间处理任务后从远程返回的值=Thread-1
 * end task!
 * 耗时：6
 */












