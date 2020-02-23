package com.rex.chapter1.T19;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/19 22:53
 * @Version 1.0
 */
public class MyThread extends Thread {
    private SynchronizedObject object;

    public MyThread(SynchronizedObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.printString("b","bb");
    }
}


class Run {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedObject synchronizedObject = new SynchronizedObject();

        MyThread myThread = new MyThread(synchronizedObject);

        myThread.start();

        Thread.sleep(500);
        myThread.stop();

        System.out.println(synchronizedObject);

    }
}

class SynchronizedObject {
    private String username = "a";
    private String password = "aa";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    synchronized public void printString(String username, String password) {
        try {
            this.username=username;
            Thread.sleep(1000);
            this.password=password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "SynchronizedObject{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
