package caseTest.functinalInterface;

import java.util.function.Function;

/**
 * Function-用于类型转换
 */
public class MyFunctionDemo {

    public static Integer convert(String value, Function<String,Integer> function){
        return function.apply(value);
    }

    public static void main(String[] args) {
        Integer integer = convert("666", s->Integer.parseInt(s)+222);
        System.out.println(integer);
    }


}
