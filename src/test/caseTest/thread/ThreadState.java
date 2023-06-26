package caseTest.thread;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 线程状态
 */
@SpringBootTest
public class ThreadState {

    @Test
    public void test1() {
        Thread thread1 = new Thread(() -> {

        });
        System.out.println("状态1:" + thread1.getState());
    }

    @Test
    public void test2() {
        Thread thread2 = new Thread(() -> {
            while (true) {

            }
        });
        thread2.start();
        System.out.println("状态2:" + thread2.getState());
    }

    @Test
    public void test3() throws InterruptedException {
        Object obj = new Object();
        Thread thread3 = new Thread(() -> {
            //thread3线程拿不到锁资源
            synchronized (obj) {

            }
        });

        //main线程拿到obj的锁资源
        synchronized (obj) {
            thread3.start();
            Thread.sleep(500);
            System.out.println("状态3:" + thread3.getState());
        }
    }

    @Test
    public void test4() throws InterruptedException {
        Object obj = new Object();
        Thread thread4 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread4.start();
        Thread.sleep(500);
        System.out.println("状态4:" + thread4.getState());
    }

    @Test
    public void test5() throws InterruptedException {
        Thread thread5 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread5.start();
        Thread.sleep(500);
        System.out.println("状态5:" + thread5.getState());
    }

    @Test
    public void test6() throws InterruptedException {
        Thread thread6 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread6.start();
        Thread.sleep(1000);
        System.out.println("状态6:" + thread6.getState());
    }

}
