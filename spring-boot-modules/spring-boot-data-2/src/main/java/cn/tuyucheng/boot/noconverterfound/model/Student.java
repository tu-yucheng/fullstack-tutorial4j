package cn.tuyucheng.boot.noconverterfound.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String grade;
}