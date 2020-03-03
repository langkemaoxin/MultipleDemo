package com.rex.chapter3.T3114_Wait_nofity_insert_test;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/3 22:54
 * @Version 1.0
 *
 *
 *  volatile private boolean prevIsA = false;
 *
 *  演示的效果就是线程交替进行
 *
 *  其实就是开了两种线程，信号一亮，A种线程运行，信号一关，B线程运行
 *
 *
 */
public class Run {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            BackupB backupB = new BackupB(dbTools);
            backupB.start();

            BackupA backupA = new BackupA(dbTools);
            backupA.start();
        }
    }
}


class DBTools {

    //这里是最为关键的一个，就是volatile
    volatile private boolean prevIsA = false;

    synchronized public void backupA() {
        try {
            while (prevIsA == true) {
                wait();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println("#######");
            }

            prevIsA=true;
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void backupB() {
        try {
            while (prevIsA==false){
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("**************");
            }

            prevIsA=false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BackupA extends Thread {
    private DBTools dbTools;

    BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}

class BackupB extends Thread {
    private DBTools dbTools;

    BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}