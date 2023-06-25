package caseTest.functinalInterface;

import caseTest.functinalInterface.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MyPredicateDemo {

    public static List<Student> filter(List<Student> studentList, Predicate<Student> predicate) {

        List<Student> result = new ArrayList<>();

        studentList.forEach(s->{

            if(predicate.test(s)){
                result.add(s);
            }
        });

        return result;
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"张三","M"));
        list.add(new Student(2,"李四","F"));
        System.out.println(filter(list,s->s.getSex().equals("F")));
    }


}
