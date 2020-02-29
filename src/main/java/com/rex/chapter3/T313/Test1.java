package com.rex.chapter3.T313;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/29 15:16
 * @Version 1.0
 *
 *
 * 直接运行会报错 ：Exception in thread "main" java.lang.IllegalMonitorStateException
 * 原因： 没有 “对象监视器”，也就是没有同步加锁
 *
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        String s = new String("");
        s.wait();
    }
}
