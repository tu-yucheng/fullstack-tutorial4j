package cn.tuyucheng.taketoday.performancemonitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
}