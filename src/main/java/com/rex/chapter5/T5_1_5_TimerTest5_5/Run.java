package com.rex.chapter5.T5_1_5_TimerTest5_5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 21:07
 * @Version 1.0
 * <p>
 * schedule
 *
 * 
 */
public class Run {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        System.out.println("现在执行时间"+new Date());

        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)-20);
        Date time = calendar.getTime();
        System.out.println("计划时间："+time);

        Timer timer = new Timer();
        timer.schedule(myTask, time, 2000);
    }


}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Btime" + System.currentTimeMillis());
        System.out.println("Etime" + System.currentTimeMillis());
    }
}