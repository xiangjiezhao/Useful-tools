package caseTest.thread;

/**
 * 线程状态
 */
public class No6 {


    public static void main(String[] args) throws InterruptedException {

//        Thread thread1 = new Thread(() -> {
//
//        });
//        System.out.println("状态1:" + thread1.getState());

//======================================================================

//        Thread thread2 = new Thread(() -> {
//            while (true) {
//
//            }
//        });
//        thread2.start();
//        System.out.println("状态2:" + thread2.getState());
//======================================================================

//        Object obj = new Object();
//        Thread thread3 = new Thread(() -> {
//            //thread3线程拿不到锁资源
//            synchronized (obj) {
//
//            }
//        });
//
//        //main线程拿到obj的锁资源
//        synchronized (obj){
//            thread3.start();
//            Thread.sleep(500);
//            System.out.println("状态3:" + thread3.getState());
//        }
//======================================================================

//        Object obj = new Object();
//        Thread thread4 = new Thread(() -> {
//            synchronized (obj){
//                try {
//                    obj.wait();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        thread4.start();
//        Thread.sleep(500);
//        System.out.println("状态4:" + thread4.getState());
//======================================================================

//        Thread thread5 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        thread5.start();
//        Thread.sleep(500);
//        System.out.println("状态5:" + thread5.getState());
//======================================================================
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
