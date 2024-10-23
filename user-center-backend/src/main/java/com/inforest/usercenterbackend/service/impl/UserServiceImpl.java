package com.inforest.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inforest.usercenterbackend.model.domain.User;
import com.inforest.usercenterbackend.service.UserService;
import com.inforest.usercenterbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 久居白昼
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-10-23 16:51:55
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




