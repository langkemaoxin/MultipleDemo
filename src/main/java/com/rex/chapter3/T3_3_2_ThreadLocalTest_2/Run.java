package com.rex.chapter3.T3_3_2_ThreadLocalTest_2;

import java.util.Date;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/5 22:00
 * @Version 1.0
 *
 *
 *  验证线程变量的隔离性
 *
 * 演示结果
 * Main get valueMain 1
 * ThreadB get Value=ThreadB1
 * ThreadA get Value=ThreadA1
 * ThreadB get Value=ThreadB2
 * Main get valueMain 2
 * ThreadA get Value=ThreadA2
 *
 *
 * 演示说明了
 * public static ThreadLocal tl = new ThreadLocal();
 *
 * 只要开启了一个线程，线程都拥有自己独立的数据区域
 *
 */
public class Run {

    public static ThreadLocalExt tl=new ThreadLocalExt();

    public static void main(String[] args) throws InterruptedException {
        if(tl.get()==null){
            System.out.println("111");
        }

        System.out.println(tl.get());
    }
}

class ThreadLocalExt extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return "我是默认值，第一次get不再为null";
    }

}



