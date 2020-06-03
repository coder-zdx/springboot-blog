package com.zdx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author zdx
 * @function 使用AOP切面方式进行日志内容获取
 * @date 2020/5/17 20:16
 */
@Aspect
@Component
public class LogAspect {
    //1.获取日志类
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //2.定义切面表达式,因为此为日志切面获取，所以拦截控制器
    @Pointcut("execution(* com.zdx.controller.*.*(..))")
    public void log(){}

    //3.定义前置通知
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //3.1 获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //3.2 获取JoinPoint中方法名称，类名称，方法参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();


        //3.3 将得到的信息封装到RequestLog类中
        RequestLog requestLog = new RequestLog(
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                classMethod ,
                joinPoint.getArgs()
        );

        //3.4 将封装好的信息加入logger的info中
        logger.info("Request : {}" ,requestLog);

    }

    //4.定义最终通知
    @After("log()")
    public void doAfter(){

    }

    //5.定义后置通知
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}" , result);
    }

    //6.使用简单的成员内部类进行获取日志信息封装
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
