package com.fastcompaus.mycontact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;

    @Test
    void helloWorld() {
//        System.out.println("test");
        System.out.println(helloWorldController.helloWorld());

//        assertThat(helloWorldController.helloWorld()).isEqualTo("helloWorld");
        assertThat(helloWorldController.helloWorld()).isEqualTo("HelloWorld");
    }

}