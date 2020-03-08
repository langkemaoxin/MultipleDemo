package com.rex.chapter6.T6_4_Singleton_7_1;

import java.io.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/8 22:20
 * @Version 1.0
 *
 *  内置类可以到线程安全问题，但是如果遇到序列化对象时，会发生错误
 *
 * 反序列化中使用readResolve，这样就能取出同样的对象
 */
public class Run {
    public static void main(String[] args) {

        try {

            System.out.println("1");
            MyObject instance = MyObject.getInstance();
            System.out.println("2");
            FileOutputStream fileOutputStream = new FileOutputStream(new File("myObject.txt"));

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(instance);

            objectOutputStream.close();

            fileOutputStream.close();

            System.out.println(instance.hashCode());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {


            FileInputStream fileInputStream = new FileInputStream(new File("myObject.txt"));

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            System.out.println("3");
            MyObject myObject = (MyObject) objectInputStream.readObject();
            System.out.println("4");

            fileInputStream.close();
            objectInputStream.close();

            System.out.println(myObject.hashCode());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class MyObject implements Serializable {
    private static final long serialVersionUID = 888L;

    private static class MyObjectHandler {
        private static final MyObject myobject = new MyObject();
    }

    public MyObject() {
    }

    public static MyObject getInstance() {
        return MyObjectHandler.myobject;
    }

    protected Object readResolve(){
        System.out.println("调用了 readResolve");
        return MyObjectHandler.myobject;
    }
}
