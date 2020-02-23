package com.rex.chapter2.T1_HasSelfPrivateNum;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 16:25
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreaA threaA = new ThreaA(hasSelfPrivateNum);
        threaA.start();
        ThreaB threaB = new ThreaB(hasSelfPrivateNum);
        threaB.start();
    }
}



class HasSelfPrivateNum{
    public void addI(String username) {
        int num=0;
        if(username.equals("a")){
            num=100;
            System.out.println("a set over");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            num=200;
            System.out.println("b set over");
        }

        System.out.println(username+" num="+num);
    }
}

class ThreaA extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreaA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}


class ThreaB extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreaB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}



