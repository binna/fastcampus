package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user(%s, %s, %d) value (account, email, age);
//        User user = new User();
//
//        user.setAccount("testUser02");
//        user.setEmail("testUser02@gmail.com");
//        user.setPhoneNumber("010-2222-2222");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("TestUser2");
//
//        User newUser = userRepository.save(user);
//        System.out.println("newUser : " + newUser);

        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        // select * from user where id = ?
        //Optional<User> user = userRepository.findById(1L);
//        Optional<User> user = userRepository.findByAccount("TestUser01");
//
//        user.ifPresent(selectUser -> {
//            System.out.println("user : " + selectUser);
//            System.out.println("email : " + selectUser.getEmail());
//
//            selectUser.getOrderDetailList().stream().forEach(orderDetail -> {
//                Item item = orderDetail.getItem();
//                System.out.println(item);
//            });
//        });
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if (user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("----------------????????????----------------");
                System.out.println("????????? : " + orderGroup.getRevName());
                System.out.println("????????? : " + orderGroup.getRevAddress());
                System.out.println("????????? : " + orderGroup.getTotalPrice());
                System.out.println("????????? : " + orderGroup.getTotalQuantity());

                System.out.println("----------------????????????----------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("???????????? ?????? : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("???????????? ???????????? : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("???????????? : " + orderDetail.getItem().getName());
                    System.out.println("???????????? ?????? : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("????????? ?????? : " + orderDetail.getStatus());
                    System.out.println("?????????????????? : " + orderDetail.getArrivalDate());
                });
            });
        }

        Assert.assertNotNull(user);
    }

    // @Transient ??? test ????????? ???????????? ????????? ????????????!
    @Test
    @Transient
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transient
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());        // true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);

        Assert.assertFalse(deleteUser.isPresent());  // false
    }

}
