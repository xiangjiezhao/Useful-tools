package caseTest.lambda;

import caseTest.lambda.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * lambda-排序
 */
@SpringBootTest
public class lambdaSort {

    //对list集合排序
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(2, 1, 3, 5, 4);

        //升序
        list.sort(Integer::compareTo);
        System.out.println("升序:"+list);

        //降序
        list.sort(Comparator.reverseOrder());
        System.out.println("降序:"+list);
    }

    //对对象集合排序
    @Test
    public void test2(){
        List<User> users = new ArrayList<>();





    }



}
