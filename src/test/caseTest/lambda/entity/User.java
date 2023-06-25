package caseTest.lambda.entity;

import lombok.Data;

@Data
public class User {

    private String name;

    private String age;

    private String sex;

    public User(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
