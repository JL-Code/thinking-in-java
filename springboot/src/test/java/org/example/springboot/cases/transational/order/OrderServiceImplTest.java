package org.example.springboot.cases.transational.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService service;

    @Test
    void build() {
        Order order = new Order();
        order.setMemberId("");

        service.build(order);
    }
}