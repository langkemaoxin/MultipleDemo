package com.rex.chapter5.T5_1_5_TimerTest5_4;

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
 * 如果执行任务的时间，超过周期性时间
 * 则下一次执行任务的开始时间 = 上一次结束时间
 * 
 */
public class Run {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Calendar calendar=Calendar.getInstance();
        System.out.println(System.currentTimeMillis());
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(myTask, time, 2000);
    }


}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Btime" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Etime" + System.currentTimeMillis());
    }
}