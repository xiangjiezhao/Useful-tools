package caseTest.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * lambda-遍历集合
 */
public class lambdaForEach {

    public static void main(String[] args) {

        String[] language = {
                "c","c++","c#","java","python","go","hive","php"
        };

        List<String> list = Arrays.asList(language);

        list.forEach(s-> System.out.println(s+","));
    }

}
