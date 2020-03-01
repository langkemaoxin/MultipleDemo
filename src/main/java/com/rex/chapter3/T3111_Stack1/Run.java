package com.rex.chapter3.T3111_Stack1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/1 16:23
 * @Version 1.0
 *
 *
 * 一个消费者，一个生产者
 * 使用堆栈的方式，一个pop，一个push
 */
public class Run {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P p = new P(myStack);
        C c = new C(myStack);

        ThreadP threadP = new ThreadP(p);
        threadP.start();

        ThreadC threadC = new ThreadC(c);
        threadC.start();
    }
}

class MyStack {
    private List list = new ArrayList();

    synchronized public void push() {
        try {

            if (list.size() != 0) {
                this.wait();
            }

            list.add("" + Math.random());
            this.notify();
            System.out.println("Push Size=" + list.size());

        } catch (Exception ex) {

        }
    }

    synchronized public String pop() {
        String returnV = "";

        try {
            if (list.size() == 0) {
                this.wait();
            }

            returnV = "" + list.remove(0);
            this.notify();
            System.out.println("Pop Size=" + list.size());

        } catch (Exception e) {

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