package com.lssemail.garden.book.interceptors;

import com.lssemail.garden.book.model.User;
import com.lssemail.garden.book.myanno.LoginRequired;
import com.lssemail.garden.book.service.UserService;
import com.lssemail.garden.book.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Administrator on 2018-8-6.
 */
@SpringBootConfiguration
public class AuthenticationInterceptor implements HandlerInterceptor {

    public final static String ACCESS_TOKEN = "accessToken";

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //判断接口是否需要登陆
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if(methodAnnotation != null){

            String accessToken = req.getHeader("Authorization");
            if(Objects.isNull(accessToken)){
                throw new Exception("无token，请重新登录");
            }else {
                Claims claims;
                claims = TokenUtils.parseJWT(accessToken);

                String id = claims.getId();
                User user = userService.getOne(id);
                if(Objects.isNull(user)){
                    throw new Exception("用户不存在");
                }

                req.setAttribute("user", user);
                return true;
            }
        }

        return false;
    }
}
