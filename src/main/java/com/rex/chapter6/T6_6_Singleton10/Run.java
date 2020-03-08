package com.rex.chapter6.T6_6_Singleton10;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 22:42
 * @Version 1.0
 * <p>
 * 使用枚举类构造单例模式
 *
 * 使用内部枚举类，更好的封装单例模式
 *
 * 枚举类有个特性，就是一旦加载后，就不会再执行了，保证了线程安全
 *
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

class Myconection {
    public Myconection() {
        System.out.println("public Myconection(){");
    }
}

class MyObjct {

    private enum MyEnumSingleton {
        connectionFactory;

        private Myconection connection;

        //枚举类也有构造函数
        MyEnumSingleton() {
            System.out.println("调用了MyEnumSingleton的构造");
            connection = new Myconection();
        }

        public Myconection getMyConnection(){
            return connection;
        }
    }

    //枚举类还可以有方法
    public static Myconection getConnection() {
        System.out.println("调用了 public static Myconection getConnection() {");
        return MyEnumSingleton.connectionFactory.getMyConnection();
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(MyObjct.getConnection().hashCode());
        }
    }
}