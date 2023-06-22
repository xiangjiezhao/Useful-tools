package caseTest.thread.entity;

public class MyJob extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("MyJob:"+i);
        }
    }

}
