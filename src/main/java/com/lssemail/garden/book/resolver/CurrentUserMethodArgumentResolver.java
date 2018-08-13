package com.lssemail.garden.book.resolver;

import com.lssemail.garden.book.model.User;
import com.lssemail.garden.book.myanno.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Created by Administrator on 2018-8-6.
 * @Description: 自定义参数解析器
 * 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
    * supportsParameter：用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument。
    *resolveArgument：真正用于处理参数分解的方法，返回的Object就是controller方法上的形参对象。
    */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("----------supportsParameter-----------" + parameter.getParameterType());
        //判断是否能转成UserBase 类型 & 是否有CurrentUser注解
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("--------------resolveArgument-------------" + methodParameter);
        User user = (User) nativeWebRequest.getAttribute("user", RequestAttributes.SCOPE_REQUEST);
        if (user != null) {
            return user;
        }
        throw new MissingServletRequestPartException("user");
    }
}
