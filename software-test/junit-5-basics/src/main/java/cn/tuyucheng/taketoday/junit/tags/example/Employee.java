package cn.tuyucheng.taketoday.junit.tags.example;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
}