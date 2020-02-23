package com.rex.chapter2.T218_synNotExtends;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/23 11:26
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        Sub sub = new Sub();
        MyThreadA myThreadA = new MyThreadA(sub);
        myThreadA.setName("a");
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(sub);
        myThreadB.setName("b");
        myThreadB.start();
    }
}


class MyThreadA extends Thread{
    private Sub sub;

    public MyThreadA(Sub sub) {
        this.sub = sub;
    }

    @Override
    public void run() {
        sub.serviceMethod();
    }
}


class MyThreadB extends Thread{
    private Sub sub;

    public MyThreadB(Sub sub) {
        this.sub = sub;
    }

    @Override
    public void run() {
        sub.serviceMethod();
    }
}


class Main{
    synchronized public void serviceMethod(){
        System.out.println("int main 下一步 slepp begin threadName="
        + Thread.currentThread().getName()
        + " time="+System.currentTimeMillis());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("int main 下一步 slepp end threadName="
                + Thread.currentThread().getName()
                + " time="+System.currentTimeMillis());
    }
}


class Sub extends Main{

    @Override
    public void serviceMethod() {
        System.out.println("int sub 下一步 slepp begin threadName="
                + Thread.currentThread().getName()
                + " time="+System.currentTimeMillis());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("int sub 下一步 slepp end threadName="
                + Thread.currentThread().getName()
                + " time="+System.currentTimeMillis());

        /*
        230/7  40
        * */

        super.serviceMethod();
    }
}