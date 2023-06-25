package caseTest.functinalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer-用于数据获取
 */
public class MyConsumerDemo {

    public static void foreach(List<String> list, Consumer<String> consumer) {
        list.forEach(consumer::accept);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("python");
        list.add("go");
        list.add("hive");
        foreach(list,(s)-> System.out.println(s+","));
    }


}
