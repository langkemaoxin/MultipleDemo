package com.rex.chapter7.T7_5_3_ThreadCreateExcepion.T7_5_2_ThreadCreateExcepion;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 22:24
 * @Version 1.0
 * <p>
 * <p>
 * 外部捕获线程异常
 * <p>
 *
 *     在类上直接 设置默认的错误处理器Handler
 *
 *     public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
 *         SecurityManager sm = System.getSecurityManager();
 *         if (sm != null) {
 *             sm.checkPermission(
 *                 new RuntimePermission("setDefaultUncaughtExceptionHandler")
 *                     );
 *         }
 *
 *          defaultUncaughtExceptionHandler = eh;
 *      }
 *
 * }
 */
public class Run {
    public static void main(String[] args) {
        MyThread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程AAAADDD" + t.getName() + "出现了异常");
            }
        });


        MyThread myThread = new MyThread();
        myThread.setName("A");

        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }
}
