package com.example.study.sample;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.OrderDetailStatus;
import com.example.study.model.enumclass.OrderGroupStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class OrderDetailSample extends StudyApplicationTests {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void createOrder() {
        List<User> userList = userRepository.findAll();

        for(int j = 0; j < 1; j++){
            User user = userList.get(j);
            item(user);
        }

        userList.forEach(user -> {
            int orderCount = random.nextInt(10) + 1;

            for (int i = 0; i < orderCount; i++) {
                item(user);
            }
        });
    }


    private void item(User user){
        double totalAmount = 0;

        List<Item> items = new ArrayList<>();
        List<OrderDetail> orderHistoryDetails = new ArrayList<>();

        int itemCount = random.nextInt(10) + 1;
        for(int i = 0 ; i < itemCount; i ++) {
            // db??? ????????? ????????? ??? 405???
            int itemNumber = random.nextInt(405) + 1;

            Item item = itemRepository.findById((long)itemNumber).get();
            totalAmount += item.getPrice().doubleValue();
            items.add(item);
        }

        int s = random.nextInt(3) + 1;
        OrderGroupStatus status = OrderGroupStatus.ORDERING;
        PaymentType paymentType = PaymentType.BANK_TRANSFER;
        switch (s) {
            case 1 :
                status = OrderGroupStatus.ORDERING;
                paymentType = PaymentType.BANK_TRANSFER;
                break;

            case 2 :
                status = OrderGroupStatus.COMPLETE;
                paymentType = PaymentType.CARD;
                break;

            case 3 :
                status = OrderGroupStatus.COMPLETE;
                paymentType = PaymentType.CASH;
                break;
        }

        int t = random.nextInt(2)+1;
        OrderType type = t==1? OrderType.ALL:OrderType.EACH;

        OrderGroup orderGroup = OrderGroup.builder()
                .user(user)
                .status(status)
                .orderType(type)
                .revAddress("????????? ????????? ????????????")
                .revName(user.getEmail())
                .paymentType(paymentType)
                .totalPrice(new BigDecimal(totalAmount))
                .orderAt(getRandomDate())
                .totalQuantity(itemCount)
                .arrivalDate(getRandomDate().plusDays(3).toLocalDate())
                .orderDetailList(orderHistoryDetails)
                .build();
        orderGroupRepository.save(orderGroup);

        for(Item item : items){

            OrderDetailStatus orderDetailStatus = OrderDetailStatus.ORDERING;
            switch (random.nextInt(3) + 1){
                case 1 :
                    orderDetailStatus =  OrderDetailStatus.ORDERING;
                    break;

                case 2 :
                    orderDetailStatus = OrderDetailStatus.COMPLETE;
                    break;

                case 3 :
                    orderDetailStatus = OrderDetailStatus.CONFIRM;
                    break;
            }

            OrderDetail orderDetail = OrderDetail.builder()
                    .orderGroup(orderGroup)
                    .item(item)
                    .arrivalDate(type.equals(OrderType.ALL) ?
                            orderGroup.getArrivalDate() : getRandomDate().toLocalDate())
                    .quantity(1)
                    .totalPrice(new BigDecimal(totalAmount))
                    .status(orderDetailStatus)
                    .build();
            orderDetailRepository.save(orderDetail);
            orderHistoryDetails.add(orderDetail);
        }
    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019, getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11) + 1;
    }
}
