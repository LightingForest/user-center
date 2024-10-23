package com.inforest.usercenterbackend.service;
import java.util.Date;

import com.inforest.usercenterbackend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user=new User();
        user.setUsername("dogYupi");
        user.setUserAccount("123");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setEmail("123");
        user.setPhone("456");

        boolean result=userService.save(user);
        System.out.println(result);
        System.out.println(user.getId());


    }
}