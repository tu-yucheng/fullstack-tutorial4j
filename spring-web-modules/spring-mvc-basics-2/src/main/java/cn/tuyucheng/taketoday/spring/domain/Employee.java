package cn.tuyucheng.taketoday.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Employee {

    private long id;

    @NotNull
    @Size(min = 5)
    private String name;

    @NotNull
    @Size(min = 7)
    private String contactNumber;

    public Employee() {
        super();
    }
}