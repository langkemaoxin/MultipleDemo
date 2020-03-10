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
 *
 * 为什么SimpleDateFormat 会在多线程的环境下报错？为什么不是线程安全的
 *
 *
 * 通过查看源码发现，原来SimpleDateFormat类内部有一个Calendar对象引用,
 * 它用来储存和这个SimpleDateFormat相关的日期信息,
 * 例如sdf.parse(dateStr),sdf.format(date) 诸如此类的方法参数传入的日期相关String,Date等等,
 * 都是交由Calendar引用来储存的.这样就会导致一个问题,如果你的SimpleDateFormat是个static的,
 * 那么多个thread 之间就会共享这个SimpleDateFormat, 同时也是共享这个Calendar引用。
 * 单例、多线程、又有成员变量（这个变量在方法中是可以修改的），这个场景是不是很像servlet，
 * 在高并发的情况下，容易出现幻读成员变量的现象，故说SimpleDateFormat是线程不安全的对象。
 *
 *
 *
 *
 *  private StringBuffer format(Date date, StringBuffer toAppendTo,
 *                                 FieldDelegate delegate) {
 *         // Convert input date to time field list
 *         calendar.setTime(date);
 *
 *         boolean useDateFormatSymbols = useDateFormatSymbols();
 *         ......
 *  }
 *
 *
 * 1、将SimpleDateFormat定义成局部变量。
 *
 * 缺点：每调用一次方法就会创建一个SimpleDateFormat对象，方法结束又要作为垃圾回收。
 *
 * 2、方法加同步锁synchronized，在同一时刻，只有一个线程可以执行类中的某个方法。
 *
 * 缺点：性能较差，每次都要等待锁释放后其他线程才能进入。
 *
 * 3、使用第三方库joda-time，由第三方考虑线程不安全的问题。（可以使用）
 *
 * 4、使用ThreadLocal：每个线程拥有自己的SimpleDateFormat对象。（推荐使用）
 *
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
