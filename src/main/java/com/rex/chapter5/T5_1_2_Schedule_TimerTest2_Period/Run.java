package com.rex.chapter5.T5_1_2_Schedule_TimerTest2_Period;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 18:08
 * @Version 1.0
 *
 *
 * schedule(任务，开始时间，执行周期)
 */
public class Run {
}

class MyTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("任务执行时间为："+System.currentTimeMillis());
    }
}

class Test1{
    public static void main(String[] args) {
        System.out.println("当前时间为："+new Date());
        Calendar calendarRef=Calendar.getInstance();
        calendarRef.add(Calendar.SECOND,10);
        Date runDate=calendarRef.getTime();

        System.out.println("计划执行时间："+runDate);
        MyTask myTask = new MyTask();
        Timer timer = new Timer();
        timer.schedule(myTask,runDate,4000);

    }
}















