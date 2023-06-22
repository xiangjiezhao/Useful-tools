package caseTest.thread;

import caseTest.thread.entity.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程-实现Callable 重写call方法，配合FutureTask(一般用于有返回结果的非阻塞的执行方法)
 */
public class No5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("执行一些操作:"+i);
        }
        Object count = futureTask.get();
        System.out.println("总和为:"+count);
    }




}
