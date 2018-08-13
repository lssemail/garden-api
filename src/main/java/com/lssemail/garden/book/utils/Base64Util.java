package com.lssemail.garden.book.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Base64;

/**
 * Created by Administrator on 2018-8-6.
 */
public class Base64Util {


    public static void main(String[] args) {

        JSONObject obj = new JSONObject();
        obj.put("typ", "JWT");
        obj.put("alg", "HS256");
        byte[] bytes = obj.toString().getBytes();
        String result = Base64.getEncoder().encodeToString(bytes);
        System.out.println(result);
    }
}
