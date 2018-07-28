package com.lssemail.garden.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-7-28.
 * @author
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/redis/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> redis(@PathVariable String key){

        Map<String, String> map = new HashMap<>();
        RedisOperations<String,String> ops = redisTemplate.opsForList().getOperations();
        String result = redisTemplate.opsForValue().get(key);
        map.put("result", result);
        return map;
    }

    @RequestMapping("/add/{key}/{value}")
    @ResponseBody
    public String addString(@PathVariable String key, @PathVariable String value){

        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        System.out.println("执行操作后的result:" + result);
        return result;
    }

}
