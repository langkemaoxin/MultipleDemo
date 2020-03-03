package com.rex.chapter3.T3113_pipeInputOutput;

import java.io.*;

/**
 *
 * 通过管道进行线程间通信：字符流
 *
 * 该项目演示了进程间如何使用字符流进行通信
 */
public class Run {
    public static void main(String[] args) {
        try {

            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedReader inputStream = new PipedReader();
            PipedWriter outputStream = new PipedWriter();

            //inputStream.connect(outputStream);
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();

            Thread.sleep(5000);

            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();

            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


/**
 * 写数据 OutPut
 */
class WriteData {
    public void writeMethod(PipedWriter out) {
        try {
            System.out.println("write:");

            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData);
                System.out.println( "writeMethod " +outData);
                Thread.sleep(1000);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){

        }
    }
}

/**
 * 读取数据 InPut
 */
class ReadData {
    public void readMethod(PipedReader input) {
        try {
            System.out.println("read :");

            //注意：这里使用的是 字符数组
            char[] byteArray = new char[20];
            int readlength = input.read(byteArray);
            while (readlength != -1) {
                String newData = new String(byteArray, 0, readlength);
                System.out.println("readMethod "+newData);
                readlength = input.read(byteArray);
            }

            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite extends Thread {
    private WriteData write;
    private PipedWriter out;

    ThreadWrite(WriteData write, PipedWriter out) {
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}

class ThreadRead extends Thread {
    private ReadData read;
    private PipedReader input;

    ThreadRead(ReadData read, PipedReader input) {
        this.read = read;
        this.input = input;
    }

    @Override
    public void run() {
        read.readMethod(input);
    }
}