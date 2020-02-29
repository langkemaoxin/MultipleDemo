package com.rex.chapter3.T313;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author GY.C
 * @Date 2020/2/29 15:19
 * @Version 1.0
 *
 *
 *
 * 运行结果为
 * syn上面
 * syn 第一行
 *
 *
 * 只有对当前对象进行锁定后，才能使用wait()方法 ，等待，否则就会报错
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String();
        System.out.println("syn上面");
        synchronized (lock){
            System.out.println("syn 第一行");
            lock.wait();
            System.out.println("wait下面的代码");
        }
        System.out.println("syn下面的代码");
    }
}
