package com.inforest.usercenterbackend.service;

import com.inforest.usercenterbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author 久居白昼
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-10-23 16:51:56
*/
public interface UserService extends IService<User> {
    /*
    * 用户登录状态
     */

    Long userRegister(String userAccount,String passWord,String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafeUser(User user);
}
