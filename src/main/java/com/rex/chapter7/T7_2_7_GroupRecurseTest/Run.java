package com.rex.chapter7.T7_2_7_GroupRecurseTest;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 7:34
 * @Version 1.0
 *
 *
 *
 *  recurse ：true 递归的遍历线程数组，然后放到线程组列表中
 *
 *  public int enumerate(ThreadGroup list[], boolean recurse) {
 *       checkAccess();
 *       return enumerate(list, 0, recurse);
 *  }
 */
public class Run {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup groupA = new ThreadGroup(mainGroup, "A");
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("RunMethod");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadGroup groupB = new ThreadGroup(groupA, "B");

        System.out.println("当前总共线程组数量："+Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup[] listGroup1 = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

        Thread.currentThread().getThreadGroup().enumerate(listGroup1,true);

        for (int i = 0; i < listGroup1.length; i++) {
            if(listGroup1[i]!=null){
                System.out.println(listGroup1[i].getName());
            }
        }

        System.out.println("--------------------");

        ThreadGroup[] listGroup2 = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup2,false);

        for (int i = 0; i < listGroup2.length; i++) {
            if(listGroup2[i]!=null){
                System.out.println(listGroup2[i].getName());
            }
        }


    }
}



























