package com.rex.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicTest
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/12 23:16
 * @Version 1.0
 */
public class AtomicTest {
    private static AtomicInteger index=new AtomicInteger(10);

    public static void main(String[] args) {
        new Thread(()->{
            index.compareAndSet(10,11);
            index.compareAndSet(11,10);
            System.out.println(Thread.currentThread().getName()+": 10->11->10");

        },"张三").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean isSuccess = index.compareAndSet(10, 12);
                System.out.println(Thread.currentThread().getName()+":index是预期的10?"+isSuccess
                +" 设置的新值是："+index.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"李四").start();
    }
}
