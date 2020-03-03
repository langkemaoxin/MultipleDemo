package com.rex.chapter3.T3112_pipeInputOutput;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * 通过管道进行线程间通信：字节流
 *
 * 该项目演示了进程间如何进行通信
 *
 * 在管道没有数据的时候，这里会进行阻塞
 * int readlength = input.read(byteArray);
 *
 */
public class Run {
    public static void main(String[] args) {
        try {

            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            //inputStream.connect(outputStream);
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();

            Thread.sleep(2000);

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
    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write:");

            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.println( "writeMethod " +outData);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 读取数据 InPut
 */
class ReadData {
    public void readMethod(PipedInputStream input) {
        try {
            System.out.println("read :");
            byte[] byteArray = new byte[20];
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
    private PipedOutputStream out;

    ThreadWrite(WriteData write, PipedOutputStream out) {
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
    private PipedInputStream input;

    ThreadRead(ReadData read, PipedInputStream input) {
        this.read = read;
        this.input = input;
    }

    @Override
    public void run() {
        read.readMethod(input);
    }
}