package cn.tuyucheng.taketoday.multiplecachemanager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private int orderId;
    private int itemId;
    private int quantity;
    private int customerId;
}