package com.rex.chapter7.T7_4_2_SimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 8:03
 * @Version 1.0
 *
 * 解决的方式是：每次执行parse或者 format时，都创建一个新的对象进行处理
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


            //字符串转换成格式
            Date dateRef = DateTools.parse("yyyy-MM-dd",dateString);

            //转换成指定的日期格式
            String newDateString = DateTools.format("yyyy-MM-dd",dateRef).toString();

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

class DateTools{
    public static Date parse(String formatPattern,String dateString) throws ParseException {
        System.out.println("new SimpleDateFormat");
        return new SimpleDateFormat(formatPattern).parse(dateString);
    }

    public static String format(String formatPattern,Date date){
        System.out.println("new SimpleDateFormat");
        return new SimpleDateFormat(formatPattern).format(date).toString();
    }
}





















