package com.xia.service;

import com.xia.pojo.Users;

public interface UsersService {

    public String loginVerify(String loginName,String Password);

    //密码加盐存储(用户注册)
    public int salt(String loginName,String password);
}
