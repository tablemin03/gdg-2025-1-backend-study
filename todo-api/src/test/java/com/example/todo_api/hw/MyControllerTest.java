package com.example.todo_api.hw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyControllerTest {

    @Autowired
    MyController myController;

    @Test
    void controllerTest(){
        myController.controllerMethod();
    }
}
