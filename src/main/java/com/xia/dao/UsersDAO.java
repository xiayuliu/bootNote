package com.xia.dao;

import com.xia.pojo.UserInfo;
import com.xia.pojo.Users;

public interface UsersDAO {

    public UserInfo loginVerify(String userName);

    //将密码存储到数据库中
    public int storage(UserInfo userInfo);
}
