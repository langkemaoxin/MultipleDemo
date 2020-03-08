package com.rex.chapter5.T5_1_5_TimerTest5_6;

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
 * scheduleAtFixedRate
 *
 * 如果执行的时间 晚于现在时间，那么 scheduleAtFixedRate就会把 之前的任务再次补充完整
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
        timer.scheduleAtFixedRate(myTask, time, 2000);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Btime" + new Date());
        System.out.println("Etime" +  new Date());
    }
}