package com.rex.chapter1.T181_suspend_resume_test;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 9:17
 * @Version 1.0
 */
public class MyThread extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }


    @Override
    public void run() {
        while (true) {
            i++;
        }
    }
}

class Run{
    public static void main(String[] args) {
        try{

            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);

            //A
            thread.suspend(); //线程挂起
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(1000);
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());

            //B
            thread.resume();//线程唤起
            Thread.sleep(1000);

            //C
            thread.suspend(); //线程挂起
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(1000);
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());



        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
