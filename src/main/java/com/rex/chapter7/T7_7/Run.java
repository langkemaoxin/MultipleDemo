package com.rex.chapter7.T7_7;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 22:47
 * @Version 1.0
 *
 * 线程异常优先级
 * 1. 给具体的线程设置异常处理，如果已经处理，则不会向上抛了
 * 2. 设置默认的线程异常处理
 * 3. 线程组上面的异常处理
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();


        MyThread.setDefaultUncaughtExceptionHandler(new SateUncaughtExceptionHandler());

        //myThread.setUncaughtExceptionHandler(new ObjectUncautheExceptionHandler());

        myThread.start();
    }
}

class Run2 {
    public static void main(String[] args) {

        MyThreadGroup group = new MyThreadGroup("我的线程组");

        MyThread myThread = new MyThread(group,"我的线程");

        //对象
        //myThread.setUncaughtExceptionHandler(new ObjectUncautheExceptionHandler());

        //类
        MyThread.setDefaultUncaughtExceptionHandler(new SateUncaughtExceptionHandler());

        myThread.start();


    }
}


class MyThread extends Thread {
    private String num = "a";

    public MyThread() {

    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        Integer.parseInt(num);
        System.out.println("线程打印" + num);
    }
}


class MyThreadGroup extends ThreadGroup {

    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        System.out.println("线程组的异常");
        e.printStackTrace();
    }
}

class ObjectUncautheExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("对象的异常处理");
        e.printStackTrace();
    }
}

class SateUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("静态的异常处理");
        e.printStackTrace();
    }
}
