package com.rex.chapter2.T221;

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

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //取最开始的时间
        long beginTime = CommonUtils.beginTime1;
        if(CommonUtils.beginTime2<CommonUtils.beginTime1){
            beginTime=CommonUtils.beginTime2;
        }

        //取最后的时间
        long endTime = CommonUtils.endTime1;
        if(CommonUtils.endTime2>CommonUtils.endTime1){
            endTime=CommonUtils.endTime2;
        }

        System.out.println("耗时："+(endTime-beginTime)/1000);
    }
}

class Task{
    private String getData1;
    private String getData2;
    public synchronized void doLongTimeTask(){

        String name = Thread.currentThread().getName();

        try {
            System.out.println("begin Task");
            Thread.sleep(3000);
            getData1="长时间处理任务后从远程返回的值="+name;
            getData2="长时间处理任务后从远程返回的值="+name;
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CommonUtils{
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

class MyThread1 extends Thread{
    private Task task;

    MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1=System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1=System.currentTimeMillis();
    }
}


class MyThread2 extends Thread{
    private Task task;

    MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime2=System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime2=System.currentTimeMillis();
    }
}


/**
 *
 * 演示说明：
 * 同步方法是阻塞的，一个线程执行完才能下一个线程执行。非常耗时
 *
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
 *
 *
 *
 */












