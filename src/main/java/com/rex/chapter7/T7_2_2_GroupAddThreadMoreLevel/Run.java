package com.rex.chapter7.T7_2_2_GroupAddThreadMoreLevel;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/9 22:39
 * @Version 1.0
 *
 * 多级关联
 * 父对象有子对象，子对象有孙子对象，形成一个线程树结构
 *
 * 在当前parent组上，新建一个线程组，名字为name
 * public ThreadGroup(ThreadGroup parent, String name)
 *
 * 在线程group中，新建一个可运行线程
 * public Thread(ThreadGroup group, Runnable target)
 *
 * 复制当前的线程组到指定的线程Group数组中
 * public int enumerate(ThreadGroup list[])
 *
 * 复制当前的线程列表，到指定的线程数组中国
 * public int enumerate(Thread list[])
 */
public class Run {
    public static void main(String[] args) {
        //1. 获得当前对象组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        //2. 在main组中添加一个线程组A
        ThreadGroup group = new ThreadGroup(mainGroup, "A");

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("RunMethod");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //3. 在这线程组A中，添加线程对象Z
        Thread newThread = new Thread(group, runnable);
        newThread.setName("Z");
        newThread.start();


        //4. 获取数量
        System.out.println("主线程的线程组数量为："+Thread.currentThread().getThreadGroup().activeGroupCount());

        //5. 构建当前线程数量组
        ThreadGroup[] listGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

        //6. 把当前的线程组拷贝到指定的线程组中
        Thread.currentThread().getThreadGroup().enumerate(listGroups);

        System.out.println("main 线程中有多少个子线程组："+listGroups.length+" 名字为："+listGroups[0].getName());

        //7. 构建线程数组，根据线程组中的激活数量来
        Thread[] listThread = new Thread[listGroups[0].activeCount()];

        //8. 把线程组中的线程 拷贝到 空白线程数组中
        listGroups[0].enumerate(listThread);

        //9. 得到线程名称
        System.out.println(listThread[0].getName());

    }
}
