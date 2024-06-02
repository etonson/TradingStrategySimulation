package com.example.demo.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * @author : eton.lin
 * @description TODO
 * @date 2024-05-16 下午 11:58
 */
@Aspect
@Component
public class ApiCallLog {
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.example.demo..*.*(..))")
    public void apiCallLog() {
    }

    @Before("apiCallLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            threadLocal.set(System.currentTimeMillis());
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            logger.info("URL:" + request.getRequestURL().toString());
            logger.info("HTTP方法: " + request.getMethod());
            logger.info("IP地址:" + request.getRemoteAddr());
            logger.info("類的名稱 : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName());
            logger.info("参数 : " + request.getQueryString());
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                logger.info("paramName : " + request.getParameter(paramName));
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @AfterReturning(pointcut = "apiCallLog()", returning = "retObject")
    public void doAfterReturning(Object retObject) {
        logger.info("response : " + retObject);
        logger.info("時長: " + (System.currentTimeMillis() - threadLocal.get()));
    }

    @AfterThrowing(pointcut = "apiCallLog()", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        logger.info("例外:" + ex);
    }
}
