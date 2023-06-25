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
        User user1 = new User("张三", "15", "男");
        User user2 = new User("李四", "10", "男");
        User user3 = new User("王五", "10", "男");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        //年龄升序
        users.sort(Comparator.comparing(User::getAge));
        System.out.println(users);
        //姓名降序
        users.sort(Comparator.comparing(User::getName).reversed());
        System.out.println(users);
        //先按性别排，相同再按年龄排
        users.sort(Comparator.comparing(User::getSex).reversed().thenComparing(User::getAge));
        System.out.println(users);
    }

}
