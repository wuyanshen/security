package com.web.security.config.jwtConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * @author YanShen.Wu
 * @date 2018/5/22 17:59:24
 */
public class JSONResult{
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }
}
