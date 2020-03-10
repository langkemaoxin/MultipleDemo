package com.rex.chapter7.T7_4_1_SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 8:03
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArry = new String[]{"2000-01-01","2000-01-02","2000-01-03"};
        MyThread[] myThreads = new MyThread[3];
        for (int i = 0; i < 3; i++) {
            myThreads[i] = new MyThread(sdf, dateStringArry[i]);
        }

        for (int i = 0; i < 3; i++) {
            myThreads[i].start();
        }
    }
}

class MyThread extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {

            System.out.println(dateString);

            //字符串转换成格式
            Date dateRef = sdf.parse(dateString);

            //转换成指定的日期格式
            String newDateString = sdf.format(dateRef).toString();

            if (!newDateString.equals(dateString)) {
                System.out.println("ThreadName=" + this.getName()
                        + "报错了 日期字符串" + dateString + "转换成的日期为："
                        + newDateString
                );
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

}
