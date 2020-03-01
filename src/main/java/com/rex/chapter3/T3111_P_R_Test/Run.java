package com.rex.chapter3.T3111_P_R_Test;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/1 11:28
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        String lock = "";
        P p = new P(lock);
        C c = new C(lock);
        ThreadP threadP = new ThreadP(p);
        threadP.start();

        ThreadC threadC = new ThreadC(c);
        threadC.start();
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
                Thread.sleep(500);

                if(!ValueObject.value.equals("")){
                    //等待
                    lock.wait();
                }

                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set的值是"+value);
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
                Thread.sleep(500);
                //当前的数据为空时，则阻塞线程，然后等待
                if(ValueObject.value.equals("")){
                    lock.wait();
                }

                System.out.println("get的值是"+ValueObject.value);
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
