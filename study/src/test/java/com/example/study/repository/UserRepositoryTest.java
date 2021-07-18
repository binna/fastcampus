package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user(%s, %s, %d) value (account, email, age);
        User user = new User();

        user.setAccount("testUser03");
        user.setEmail("testUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(2l);

        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });
    }

    // @Transient 을 test 코드에 사용하면 알아서 롤백해줌!
    @Test
    @Transient
    public void update() {
        Optional<User> user = userRepository.findById(2l);

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
        Optional<User> user = userRepository.findById(1l);

        Assert.assertTrue(user.isPresent());        // true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2l);

        Assert.assertFalse(deleteUser.isPresent());  // false
    }
}
