package com.wy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: MainController控制器切面
 *
 * @author wangyuan
 * Date: Created at 2018-03-24 23:29
 */
//@EnableAspectJAutoProxy
@Aspect
public class MainControllerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainControllerAspect.class);

    @Around(value = "execution(* com.wy.controller..*.*(..)) && args(request, response)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, HttpServletRequest request,
                         HttpServletResponse response) throws Throwable{
        LOGGER.info("Around Aspect begin..., method:{}" + proceedingJoinPoint.getSignature().toLongString());
        LOGGER.info("around aspect, cookies:{}" + request.getPathInfo());
        request.setAttribute("name", "hello world");
        Object returnValue = proceedingJoinPoint.proceed();
        LOGGER.info("result:" + returnValue);
        LOGGER.info("Around Aspect end..., method:" + proceedingJoinPoint.getSignature().toLongString());
        return returnValue;
    }
}
