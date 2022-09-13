package com.alexra.my_notebook.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect {

    @Around("execution(* com.alexra.my_notebook.dao.*.*(..))")
    public Object arroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        System.out.println("AOP Begin of "+ methodName);
        Object targetMethodResult = proceedingJoinPoint.proceed();
        System.out.println("AOP End of "+ methodName);
        return targetMethodResult;
    }

    @Around("execution(* com.alexra.my_notebook.controller.*.*(..))")
    public Object arroundAllControllerMethodAdvice(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        System.out.println("AOP Controller Begin of "+ methodName);
        Object MethodResult = proceedingJoinPoint.proceed();
        System.out.println("AOP Controller End of "+ methodName);
        return MethodResult;

    }
}
