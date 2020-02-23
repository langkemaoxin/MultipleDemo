package com.rex.chapter1.T4_ThreadSafe;

/**
 * @ClassName Run
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/16 22:32
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        Alogin a = new Alogin();
        a.start();

        BLogin b = new BLogin();
        b.start();
    }
}

class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    public static void doPost(String username, String password) {
        try {
            usernameRef = username;
            passwordRef = password;
            if(username.equals("a")){
                Thread.sleep(5000);
            }

            System.out.println("userName="+usernameRef +" password="+passwordRef);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Alogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("a","aa");
    }
}

class BLogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("b","bb");
    }
}
