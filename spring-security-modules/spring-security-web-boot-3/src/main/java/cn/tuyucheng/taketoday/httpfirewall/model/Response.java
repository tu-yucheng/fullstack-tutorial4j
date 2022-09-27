package cn.tuyucheng.taketoday.httpfirewall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private int code;
    private String message;
    private long timestamp;
}