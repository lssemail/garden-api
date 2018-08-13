package com.lssemail.garden.book;

import com.lssemail.garden.book.model.Book;
import com.lssemail.garden.book.model.User;
import com.lssemail.garden.book.myanno.CurrentUser;
import com.lssemail.garden.book.myanno.LoginRequired;
import com.lssemail.garden.book.service.BookService;
import com.lssemail.garden.book.service.UserService;
import com.lssemail.garden.book.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/book/{id}")
    @ResponseBody
    public String getOne(@PathVariable Long id){

        String  title = bookService.findOne(id);
        return title;
    }

    @RequestMapping
    @ResponseBody
    public String index(HttpServletRequest request){

        String requestHeader = request.getHeader("user-agent");

        String result = "";
        if(requestHeader.contains("ANDRIOD".toLowerCase())){

        }
        return requestHeader;
    }

    @LoginRequired
    @RequestMapping(value = "/token")
    @ResponseBody
    public String token(@CurrentUser User user, String username, String token){

        logger.info(username + "----" + token);
        logger.info("----" + user.getName());
        logger.info("params==" + user.toString());
        User user1 = userService.getOne(user.getId());
        if (user1 == null) {
            return "账号不存在";
        } else {
            User result = null;
//            result = userBaseService.login(userBase);
            //生成token
            String accessToken= TokenUtils.createJwtToken(user.getId());
            logger.info("accessToken:" + accessToken);
            if (result == null) {
                return  "密码错误";
            } else {
                return "SUCCESS";
            }
        }
    }

}
