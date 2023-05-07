package com.example.captcha;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CaptchaAspect {

    @Autowired
    private CaptchaValidator validator;

    @Around("@annotation(RequiresCaptcha)")
    public Object validateCaptcha(ProceedingJoinPoint joinPoint) throws Throwable {

        var request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        String captchaResponse = request.getHeader("captcha-response");

        boolean isValid = validator.validateCaptcha(captchaResponse);
        if(!isValid){
            throw new RuntimeException("Invalid captcha");
        }
        return joinPoint.proceed(); // permite el paso hacia el método del controlador
    }

}
