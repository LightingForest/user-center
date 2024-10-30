package com.inforest.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inforest.usercenterbackend.model.domain.User;
import com.inforest.usercenterbackend.service.UserService;
import com.inforest.usercenterbackend.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.inforest.usercenterbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 久居白昼
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-10-23 16:51:55
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;

    private static final String SALT="inforest";

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        //todo 修改自定义异常
        if(StringUtils.isAllBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        if(userAccount.length()<4){
            return null;
        }
        if(userPassword.length()<8||checkPassword.length()<8){
            return null;
        }
        //账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher= Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return null;
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            return null;
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count=userMapper.selectCount(queryWrapper);
        if(count>0){
            return null;
        }
        //加密
        String encryptPassword= DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());
        //插入数据
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean save = this.save(user);
        if(!save){
            return null;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAllBlank(userAccount,userPassword)){
            return null;
        }
        if(userAccount.length()<4){
            return null;
        }
        if(userPassword.length()<8){
            return null;
        }
        //账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher= Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return null;
        }
        //加密
        String encryptPassword= DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());
        //账户是否存在
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            log.info("user login failed,userAccount cannot match userPassword");
            return null;
        }
        //用户脱敏
        User safetyUser=getSafeUser(user);

        //记录用户的登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    public User getSafeUser(User user){
        User safetyUser=new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }


}




