package com.rex.chapter7.T7_2_4_GetGroupParent;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 23:32
 * @Version 1.0
 *
 *  JVM的根线程组就是system,再取其父线程则出现空异常
 *
 *  演示效果：
 *      A处线程：main 所属线程为：main
 *      Exception in thread "main" main线程所在的线程组的父线程组的名称是：system
 *      java.lang.NullPointerException
 * 	    at com.rex.chapter7.T7_2_4_GetGroupParent.Run.main(Run.java:23)
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("A处线程：" + Thread.currentThread().getName()
                + " 所属线程为："
                + Thread.currentThread().getThreadGroup().getName() + " "
        );


        System.out.println("main线程所在的线程组的父线程组的名称是："
        + Thread.currentThread().getThreadGroup().getParent().getName()
        );

        System.out.println("main线程所在的线程组的爷爷线程组的名称是："
                + Thread.currentThread().getThreadGroup().getParent().getParent().getName()
        );


    }
}
