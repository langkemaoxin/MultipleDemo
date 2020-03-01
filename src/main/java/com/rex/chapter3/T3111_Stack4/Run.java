package com.rex.chapter3.T3111_Stack4;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/1 16:23
 * @Version 1.0
 * <p>
 * <p>
 * 一个生产者，多个消费者
 *
 * 1. 这里加了一个while循环，一定是要有值，才能继续下面的操作
 * 2. 然后使用notifyall()
 *
 */
public class Run {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        P p = new P(myStack);
        ThreadP threadP = new ThreadP(p);
        threadP.start();


        C c = new C(myStack);
        C c1 = new C(myStack);
        C c2 = new C(myStack);
        C c3 = new C(myStack);

        ThreadC threadC = new ThreadC(c);
        threadC.start();

        ThreadC threadC1 = new ThreadC(c1);
        threadC1.start();

        ThreadC threadC2 = new ThreadC(c2);
        threadC2.start();

        ThreadC threadC3 = new ThreadC(c3);
        threadC3.start();
    }
}

class MyStack {
    private List list = new ArrayList();

    synchronized public void push() {
        try {

            while (list.size() != 0) {
                System.out.println("生产者:"+Thread.currentThread().getName()+"没事干，睡觉");
                this.wait();
            }

            list.add("" + Math.random());
            this.notifyAll();
            System.out.println("生产者:"+Thread.currentThread().getName()+"添加了一个值");


        } catch (Exception ex) {

        }
    }

    synchronized public String pop() {
        String returnV = "";

        try {
            while (list.size() == 0) {
                System.out.println("消费者:"+Thread.currentThread().getName()+"没事干，睡觉");
                this.wait();
            }

            System.out.println("消费者:"+Thread.currentThread().getName()+"起来干活了");
            returnV = "" + list.remove(0);
            this.notifyAll();


        } catch (Exception e) {
            System.out.println("消费者："+Thread.currentThread().getName()+"明明没事干你加我干嘛？");
        }

        return returnV;
    }
}


class P {
    private MyStack myStack;

    P(MyStack myStack) {
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }
}

class C {
    private MyStack myStack;

    C(MyStack myStack) {
        this.myStack = myStack;
    }

    public void popService() {
        this.myStack.pop();
    }
}

class ThreadP extends Thread {
    private P p;

    ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.pushService();
        }
    }
}

class ThreadC extends Thread {
    private C c;

    ThreadC(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.popService();
        }
    }
}