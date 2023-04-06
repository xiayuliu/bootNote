package com.xia.service.impl;

import com.xia.dao.UsersDAO;
import com.xia.pojo.UserInfo;
import com.xia.pojo.Users;
import com.xia.service.UsersService;
import com.xia.util.redis.RedisKey;
import com.xia.util.salt.Saltes;
import com.xia.util.token.JWTHelper;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDAO usersDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 解密密码
     * @param loginName 账号
     * @param Password  密码
     * @return token
     */
    @Override
    public String loginVerify(String loginName, String Password) {

        UserInfo users = usersDAO.loginVerify(loginName);
        System.out.println(users.toString());
        boolean compare = Saltes.compare(users, Password);
        String toKen = JWTHelper.getToKen(loginName);
        if (compare){
            if (users!=null){
                JSONObject resolver = JWTHelper.resolver(toKen);
                String tokenId = (String) resolver.get("tokenId");
                resolver.put("token",toKen);
                stringRedisTemplate.opsForValue().set(RedisKey.REDIS_TOKEN_KEY+tokenId,resolver.toString(),14400, TimeUnit.SECONDS);
            }else {
                return null;
            }
        }else {
            return null;
        }


        return toKen;
    }


    /**
     * 注册用户，将带盐哈希密码存储到数据库中
     *
     * @param loginName 用户名
     * @param password 密码
     */
    @Override
    public int salt(String loginName, String password) {
        byte[] salt = Saltes.generateSalt(); // 生成盐
        byte[] hashedPassword =Saltes.hashPassword(password, salt); // 带盐哈希密码
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(loginName);
        userInfo.setUserPwd(Base64.getEncoder().encodeToString(hashedPassword));
        userInfo.setSalt(Base64.getEncoder().encodeToString(salt));
        int storage = usersDAO.storage(userInfo);

        return storage;
    }


}
