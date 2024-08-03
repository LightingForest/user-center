package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
 * 用户服务测试
 *
 * @author 久居白昼
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser(){
        User user=new User();
        user.setUsername("yupi");
        user.setUserAccount("123");
        user.setAvatarUrl("https://www.google.com/imgres?imgurl=https%3A%2F%2Fpic.616pic.com%2Fys_img%2F00%2F03%2F87%2Fx0UGIbbzRy.jpg&tbnid=8BIIa42uh2bzsM&vet=12ahUKEwi2sfLWkuCGAxUCVPUHHRFzEUkQMygKegQIARBW..i&imgrefurl=https%3A%2F%2F616pic.com%2Ftupian%2Frenwumanhua.html&docid=mbZpMv86x9fJaM&w=260&h=280&q=%E4%BA%BA%E7%89%A9&ved=2ahUKEwi2sfLWkuCGAxUCVPUHHRFzEUkQMygKegQIARBW");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setEmail("123");
        user.setPhone("456");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount="yupi";
        String userPassword="";
        String checkPassword="123456";
        long result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="yu";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="yupi";
        userPassword="123456";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="yu pi";
        userPassword="12345678";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        checkPassword="123456789";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="dogyupi";
        checkPassword="12345678";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="yupi";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        System.out.println(result);
        //Assertions.assertTrue(result>0);

        userAccount="wangyuqing";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        System.out.println(result);

        userAccount="jiuju";
        result=userService.userRegister(userAccount,userPassword,checkPassword);
        System.out.println(result);
    }
}