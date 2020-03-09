package com.rex.chapter7.T7_2_5_MainGroup;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 23:37
 * @Version 1.0
 *
 * 线程组里面，添加线程组
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("线程组名称"
                +Thread.currentThread().getThreadGroup().getName());

        System.out.println("线程组活动的线程数量"
                +Thread.currentThread().getThreadGroup().activeCount());

        System.out.println("线程组中线程组数量 Before："
                +Thread.currentThread().getThreadGroup().activeGroupCount());

        new ThreadGroup("newGroup");

        System.out.println("线程组中线程组数量 After："
                +Thread.currentThread().getThreadGroup().activeGroupCount());

        System.out.println("parent线程组名称"
                +Thread.currentThread().getThreadGroup().getParent().getName());

    }
}
