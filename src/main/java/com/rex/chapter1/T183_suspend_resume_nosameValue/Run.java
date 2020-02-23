package com.rex.chapter1.T183_suspend_resume_nosameValue;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/22 11:35
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        final MyObject myObject = new MyObject();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                myObject.setValue("a","aa");
            }
        };

        thread1.setName("a");
        thread1.start();
        Thread.sleep(500);

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                myObject.print();
            }
        };
        thread2.start();
    }
}

class MyObject {
    private String userName = "1";
    private String password = "11";

    public void setValue(String userName, String password) {

        this.userName = userName;

        if (Thread.currentThread().getName().equals("a")) {

            System.out.println("停止A线程");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }

    public void print() {
        System.out.println(userName + " " + password);
    }
}
