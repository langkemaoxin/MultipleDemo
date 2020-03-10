package com.rex.chapter7.T7_5_2_ThreadCreateExcepion;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 22:24
 * @Version 1.0
 *
 *
 *    外部捕获线程异常
 *
 *    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
 *         checkAccess();
 *         uncaughtExceptionHandler = eh;
 *     }
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("A");


        myThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程"+t.getName() +"出现了异常");
                e.printStackTrace();
            }
        });

        myThread.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        String username=null;
        System.out.println(username.hashCode());
    }
}
