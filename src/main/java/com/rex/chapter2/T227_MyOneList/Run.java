package com.rex.chapter2.T227_MyOneList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/28 23:23
 * @Version 1.0
 *
 *
 * 研究类似火车站抢票现象
 *
 * 多个对象同时对一个对象进行操作的时候
 *
 * 使用synchronized(x对象) 就能达到目的了
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyOneList list = new MyOneList();
        MyThrea1 myThrea1 = new MyThrea1(list);
        myThrea1.setName("A");
        myThrea1.start();


        MyThrea2 myThrea2 = new MyThrea2(list);
        myThrea2.setName("B");
        myThrea2.start();

        Thread.sleep(5000);

        System.out.println("listSize=" + list.getSize());
    }
}

class MyOneList {
    private List list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }
}

class MyService {

    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            synchronized (list) {
                if (list.getSize() < 1) {
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}

class MyThrea1 extends Thread {
    private MyOneList list;

    MyThrea1(MyOneList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list, "a");
    }
}


class MyThrea2 extends Thread {
    private MyOneList list;

    MyThrea2(MyOneList list) {
        super();
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(list, "b");
    }
}