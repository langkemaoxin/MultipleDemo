package com.rex.chapter7.T7_2_3_AutoAddGroup;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 23:01
 * @Version 1.0
 *
 *
 *  创建一个线程组，就自动加入到了当前的线程组中
 *  public ThreadGroup(String name) {
 *         this(Thread.currentThread().getThreadGroup(), name);
 *  }
 *
 *  activeGroupCount() 取得当前线程组对象中的子线程组数量
 *  enumerate() 的作用是将线程组中的子线程组以复制的形式 拷贝到 ThreadGroup[] 中
 *
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("A处线程：" + Thread.currentThread().getName()
                + " 所属线程为："
                + Thread.currentThread().getThreadGroup().getName() + " "
                + " 中有线程组数量"
                + Thread.currentThread().getThreadGroup().activeGroupCount()
        );

        //只要新建一个线程组，就自动加进入了
        new ThreadGroup("新的组");
        new ThreadGroup("新的组1");
        new ThreadGroup("新的组3");

        System.out.println("B处线程：" + Thread.currentThread().getName()
                + " 所属线程为："
                + Thread.currentThread().getThreadGroup().getName() + " "
                + " 中有线程组数量"
                + Thread.currentThread().getThreadGroup().activeGroupCount()
        );

        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

        Thread.currentThread().getThreadGroup().enumerate(threadGroups);

        for (int i = 0; i < threadGroups.length; i++) {
            System.out.println("名称为：" + threadGroups[i].getName());
        }


    }
}
