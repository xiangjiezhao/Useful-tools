package caseTest.thread;

import caseTest.thread.entity.MyRunnable;
/**
 * 多线程-实现Runnable接口，重写run方法(比继承方式好，因为java是单继承)
 * 这几种不同方式(包括线程池方式)，追其底层都是实现Runnable接口的
 */
public class No2 {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("No2:"+i);
        }
    }


}
