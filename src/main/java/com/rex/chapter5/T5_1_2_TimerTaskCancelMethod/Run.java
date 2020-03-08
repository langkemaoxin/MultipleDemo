package com.rex.chapter5.T5_1_2_TimerTaskCancelMethod;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 18:20
 * @Version 1.0
 *
 *  自己把自己从定时器中移除
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间为："+new Date());
        Calendar calendarRef=Calendar.getInstance();
        calendarRef.add(Calendar.SECOND,2);
        Date runDate=calendarRef.getTime();

        MyTaskA myTaskA = new MyTaskA();
        MyTaskB myTaskB = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(myTaskA,runDate,200);
        timer.schedule(myTaskB,runDate,200);

        Thread.sleep(10000);
        timer.cancel();
    }
}


class MyTaskA extends TimerTask{
    @Override
    public void run() {
        System.out.println("A run timer="+new Date());

        //移除自己
        this.cancel();
        System.out.println("A移除自己");
    }
}

class MyTaskB extends TimerTask{
    @Override
    public void run() {
        System.out.println("B run timer="+new Date());
 ;
    }
}

