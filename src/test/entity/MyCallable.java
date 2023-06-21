package entity;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {

        int count = 0;

        for (int i = 0; i < 100; i++) {
            count += i;
        }
        return count;
    }
}
