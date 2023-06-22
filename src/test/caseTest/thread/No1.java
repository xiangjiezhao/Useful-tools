package caseTest.thread;

import caseTest.thread.entity.MyJob;

/**
 * 多线程-继承Thread类，重写run方法
 */
public class No1 {

    public static void main(String[] args) {

        MyJob myJob = new MyJob();
        myJob.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("No1:"+i);
        }


    }


}
