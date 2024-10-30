package com.inforest.usercenterbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * @author : inforest
 *
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -6897417526117536226L;

    private String userAccount;
    private String userPassword;
}
