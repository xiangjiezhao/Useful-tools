package caseTest.functinalInterface.entity;

import lombok.Data;

@Data
public class Student {

    private Integer id;

    private String name;

    private String sex;

    public Student(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
