package com.rex.chapter3.T3111_P_R_AllWait;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/1 11:28
 * @Version 1.0
 *
 *
 * 这里研究了两组消费者，生产者
 * 互相wait，notify操作
 *
 * 但是，这份代码有一定的几率是会产生死锁的，即大家都是出于wait状态
 *
 * 这是因为：在notify的时候，是随机唤起一个线程，所以可能是唤起了同类线程，而不是异类线程
 * 导致了所有的线程都处于等待状态
 *
 *
 * 解决的方法是：使用notifyAll()
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        String lock = "";
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] threadP = new ThreadP[2];
        ThreadC[] threadC = new ThreadC[2];
        for (int i = 0; i < 2; i++) {
            threadP[i]=new ThreadP(p);
            threadP[i].setName("生产者 "+(i+1));
            threadP[i].start();

            threadC[i]=new ThreadC(c);
            threadC[i].setName("消费者 "+(i+1));
            threadC[i].start();
        }

        Thread.sleep(5000);

        Thread[] threadArr = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArr);
        for (int i = 0; i < threadArr.length; i++) {
            System.out.println(threadArr[i].getName()+ " "+threadArr[i].getState() );
        }

    }
}


class ValueObject {
    public static String value = "";
}


/**
 * 生产者
 *
 * 如果当前数据还有的话，则阻塞自己，等待别人消费完
 *
 * 如果当前没有值了，则生成一个新的值放入
 */
class P {
    private String lock;

    P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock){
                Thread.sleep(100);

                if(!ValueObject.value.equals("")){
                    //等待
                    lock.wait();
                }

                System.out.println("生产者 "+Thread.currentThread().getName()+" 正常生产 ★");
//
//                System.out.println("生产者 "+Thread.currentThread().getName()+" RUNNABLE了");

                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                ValueObject.value=value;

                //这里通知别人？
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/**
 *
 * 消费者
 *
 * 如果没有数据则等待，如果有数据了，则取出，然后通知别人
 */
class C{
    private String lock;

    C(String lock) {
        this.lock = lock;
    }

    public void getValue(){
        try{
            synchronized (lock){
                Thread.sleep(2000);
                //当前的数据为空时，则阻塞线程，然后等待
                if(ValueObject.value.equals("")){

                    lock.wait();
                }
                System.out.println("消费者 "+Thread.currentThread().getName()+" 正常消费 ☆");
//                System.out.println("消费者 "+Thread.currentThread().getName()+" RUNNABLE了");

                ValueObject.value="";
                lock.notify();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class ThreadP extends Thread{
    private P p;

    ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.setValue();
        }
    }
}


class ThreadC extends  Thread{
    private C c;

    ThreadC(C c) {
        this.c = c;
    }

    @Override
    public void run() {

        while (true){
            c.getValue();
        }
    }
}
