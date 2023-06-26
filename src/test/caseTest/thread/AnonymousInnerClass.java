package caseTest.thread;
/**
 * 多线程-匿名内部类方式
 */
public class AnonymousInnerClass {


    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("匿名内部类:" + i);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("No3:"+i);
        }



    }





}
