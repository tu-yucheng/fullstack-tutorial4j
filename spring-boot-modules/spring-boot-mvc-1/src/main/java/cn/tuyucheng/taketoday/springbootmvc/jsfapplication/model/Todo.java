package cn.tuyucheng.taketoday.springbootmvc.jsfapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {

    private int id;
    private String message;
    private int priority;
}