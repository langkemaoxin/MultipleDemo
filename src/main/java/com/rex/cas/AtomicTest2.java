package com.rex.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicTest2
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/12 23:40
 * @Version 1.0
 */
public class AtomicTest2 {
    private static AtomicInteger count =new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //每个线程让count自增100次
                    for (int i = 0; i < 100; i++) {
                        count.incrementAndGet();
                    }
                }
            }).start();
        }

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
