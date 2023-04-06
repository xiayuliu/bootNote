package com.xia.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xia.util.UtilCore;
import net.sf.json.JSONObject;

import java.util.Date;

public class JWTHelper {

    public static final String TO_KEN_KEY="2002DZT_TOKEN_LOGIN_XY";

    //生成token
    public static String getToKen(String loginName){
        return JWT.create()
                .withClaim("loginName",loginName)
                .withClaim("tokenId", UtilCore.getToKenID(6))
                .withClaim("exp",new Date(System.currentTimeMillis()+ 60 * 1000))
                .sign(Algorithm.HMAC256(TO_KEN_KEY));
    }



    //解密token将token中内容解析成json对象
    public static JSONObject resolver(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(TO_KEN_KEY)).build().verify(token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName",verify.getClaim("loginName").asString());
        jsonObject.put("tokenId",verify.getClaim("tokenId").asString());
        return jsonObject;
    }

}
