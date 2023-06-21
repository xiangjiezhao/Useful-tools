package caseTest.thread;

/**
 * 多线程-lambda方式
 */
public class No4 {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda:"+i);
            }
        });

        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("No4:"+i);
        }
    }








}
