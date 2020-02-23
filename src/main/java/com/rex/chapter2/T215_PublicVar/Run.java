package com.rex.chapter2.T215_PublicVar;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 23:45
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        PublicVar publicVar = new PublicVar();
        MyThread myThread = new MyThread(publicVar);
        myThread.start();
        Thread.sleep(100);
        publicVar.getValue();

    }
}

class PublicVar {
    private String username = "A";
    private String password = "AA";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(3000);
            this.password = password;

            System.out.println("setValue ThreaName=" + Thread.currentThread().getName()
                    + "userName=" + username
                    + "password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void getValue(){
        System.out.println("getValue ThreaName=" + Thread.currentThread().getName()
                + "userName=" + username
                + "password=" + password);
    }
}

class MyThread extends Thread{
    private PublicVar publicVar;

    public MyThread(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}