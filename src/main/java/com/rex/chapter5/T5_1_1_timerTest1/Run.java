package com.rex.chapter5.T5_1_1_timerTest1;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/7 22:37
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("当前时间为"+System.currentTimeMillis());
        Calendar calendarRef=Calendar.getInstance();
        calendarRef.add(Calendar.SECOND,3);
        Date runDate = calendarRef.getTime();

        MyTask myTask = new MyTask();
        //Timer timer = new Timer();

        Timer timer = new Timer(true);

        /**
         * 时间对象安排计划 任务，时间
         */
        timer.schedule(myTask,runDate);

    }
}

class MyTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("当前时间为"+System.currentTimeMillis());
    }
}