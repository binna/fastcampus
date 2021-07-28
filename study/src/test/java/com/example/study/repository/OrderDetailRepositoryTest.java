package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
//        OrderDetail orderDetail = new OrderDetail();

//        orderDetail.setOrderAt(LocalDateTime.now());

        // 어떤 사람?
//        orderDetail.setUserId(1L);

        // 어떤 상품?
//        orderDetail.setItemId(1L);

//        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
//        Assert.assertNotNull(newOrderDetail);

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDate.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900_000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");
//        orderDetail.setOrderGroupId(1L);
//        orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }

}
