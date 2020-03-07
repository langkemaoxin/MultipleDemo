package com.rex.chapter4.T4_1_8_ConditionTestManyToMany;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 9:31
 * @Version 1.0
 *
 * 演示效果：
 * 连续生产 xxxThread-0
 * 连续生产 xxxThread-6
 * 连续消费 ***
 * 连续消费 ***
 * 连续生产 xxxThread-2
 * 连续消费 ***
 * 连续生产 xxxThread-4
 * 连续消费 ***
 * 连续生产 xxxThread-0
 * 连续生产 xxxThread-8
 * 连续生产 xxxThread-6
 * 连续消费 ***
 * 连续消费 ***
 * 连续消费 ***
 * 连续生产 xxxThread-2
 * 连续消费 ***
 * 连续生产 xxxThread-4
 * 连续消费 ***
 * 连续生产 xxxThread-0
 * 连续生产 xxxThread-8
 * 连续生产 xxxThread-6
 * 连续消费 ***
 * 连续消费 ***
 *
 *
 * 总结：为什么会出现这种情况？
 * 1. 假设所有的消费者都因为没有值 然后都在等待中
 * 2. 有个生产者进行了生产，唤醒了其中一个消费者
 * 3. 消费者进行消费后，唤醒了同类，同类又有可能唤醒同类，导致同一组消费者同时在消费
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();

        MyThreadA[] myThreadA_Arr=new MyThreadA[5];
        MyThreadB[] myThreadB_Arr=new MyThreadB[5];
        for (int i = 0; i < 5; i++) {
            myThreadA_Arr[i]=new MyThreadA(myService);
            myThreadB_Arr[i]=new MyThreadB(myService);

            myThreadA_Arr[i].start();
            myThreadB_Arr[i].start();
        }
    }
}

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {
        try {
            lock.lock();

            while (hasValue == true) {
                System.out.println("连续生产 xxx"+Thread.currentThread().getName());
                condition.await();
            }
            hasValue=true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();

            while (hasValue == false) {
                System.out.println("连续消费 ***");
                condition.await();
            }

            hasValue=false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class MyThreadA extends Thread{
    private MyService service;

    MyThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.set();
        }
    }
}

class MyThreadB extends Thread{
    private MyService service;

    MyThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.get();
        }
    }
}
