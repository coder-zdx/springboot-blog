package com.zdx.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zdx
 * @function 统一异常处理器
 * @date 2020/5/17 15:29
 */
//加入@ControllerAdvice注解表示所有@Controller控制器都要经过此处理
@ControllerAdvice
public class ControllerExceptionHandler {
    //1.获取日志，包为slf4j.Logger
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    //2.编写处理异常方法，加上@ExceptionHandler注解，出现异常会经过此方法，Exception.class表明所有异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //2.1 将错误的url和异常写入日志的错误中,一为请求路径，通过request对象getRequestURI方法获取,一为Exception
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);

        //2.3 定义资源找不到异常后进行的补充，该语句表示若是抛出的异常上有ResponseStatus注解
        //     则直接抛出，因为其为自定义的异常·
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        //2.2 创建ModelAndView对象，将请求路径和异常信息添加到request域中，为了在页面展示
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");

        return mv;
    }
}
