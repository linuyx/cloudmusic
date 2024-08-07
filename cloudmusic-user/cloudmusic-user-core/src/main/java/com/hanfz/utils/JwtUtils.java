package com.hanfz.utils;

import com.alibaba.fastjson.JSON;
import com.hanfz.pojo.request.RequestDataApp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @Author Linuyx
 * @Description JWT工具类
 * @Date Created in 2021-08-09 9:50
 */
public class JwtUtils {

    /**
     * 过期类型(时、分、秒...)
     */
    private static final int EXPIRE_TYPE = Calendar.DAY_OF_YEAR;

    /**
     * 过期时间(24小时 * 7)
     */
    private static final int EXPIRE_TIME = 7;

    /**
     * 密钥
     */
    private static final String KEY = "Linuyx0712!@#$%^";


    /**
     * 签发JWT
     *
     * @param requestDataApp 有效载荷
     * @return jwt
     */
    public static String createJwt(RequestDataApp requestDataApp) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(EXPIRE_TYPE, EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(new HashMap<>(2){{put("requestData", JSON.toJSONString(requestDataApp));}})
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param jwt
     */
    public static RequestDataApp parseJwt(String jwt) {
        Jws<Claims> jwtResult = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
        Claims payload = jwtResult.getBody();
        String requestDataBms = payload.get("requestData", String.class);
        return JSON.parseObject(requestDataBms, RequestDataApp.class);
    }

}
