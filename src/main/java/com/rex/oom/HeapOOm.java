package com.rex.oom;

import java.util.ArrayList;

/**
 * @ClassName HeapOOm
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/15 14:00
 * @Version 1.0
 */
public class HeapOOm {
    static class OOMObject{

    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            Thread.sleep(500);
            list.add(new OOMObject());
        }
    }
}
