package com.rex.chapter6.T6_6_Singleton9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 22:42
 * @Version 1.0
 *
 * 使用枚举类构造单例模式
 */
public class Run {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        System.out.println("Begin");
    }
}

class Myconection{
    public Myconection(){
        System.out.println("public Myconection(){");
    }
}

enum MyObject{
    connectionFactory;

    private Myconection connection;

    //枚举类也有构造函数
    MyObject(){
        System.out.println("调用了MyObject的构造");
        connection= new Myconection();
    }

    //枚举类还可以有方法
    public Myconection getConnection() {
        return connection;
    }
}


class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(MyObject.connectionFactory.getConnection().hashCode());
        }
    }
}