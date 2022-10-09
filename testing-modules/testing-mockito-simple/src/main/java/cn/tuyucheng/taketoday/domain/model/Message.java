package cn.tuyucheng.taketoday.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Message {

    private String from;
    private String to;
    private String text;
    private Date date;
    private UUID id;
}