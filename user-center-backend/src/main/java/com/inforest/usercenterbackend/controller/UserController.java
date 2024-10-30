package com.inforest.usercenterbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inforest.usercenterbackend.model.domain.User;
import com.inforest.usercenterbackend.model.domain.request.UserLoginRequest;
import com.inforest.usercenterbackend.model.domain.request.UserRegisterRequest;
import com.inforest.usercenterbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inforest.usercenterbackend.constant.UserConstant.ADMIN_ROLE;
import static com.inforest.usercenterbackend.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword,request);
    }
    @GetMapping("/search")
    public List<User> searchUsers(String username,HttpServletRequest request) {
        //仅管理员查询
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        List<User> userList= userService.list(userQueryWrapper);
        //todo 着重看一下
        return userList.stream().map(user->{
            return userService.getSafeUser(user);
        }).collect(Collectors.toList());
    }
    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody Long id,HttpServletRequest request) {
        if(!isAdmin(request)){
            return false;
        }
        if(id<=0){
            return false;
        }
        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request){
        Object userObj= (request.getSession().getAttribute(USER_LOGIN_STATE));
        User user= (User) userObj;
        return user!=null&&user.getUserRole()==ADMIN_ROLE;
    }
}
