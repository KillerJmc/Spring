package com.jmc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect //标志这是切面类
@Component("myAspect")
public class MyAspect {
    //定义切点表达式
    @Pointcut("execution(* com.jmc.aop..*.*(..))")
    public void pointcut() {
        
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("前置增强...");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("后置增强...");
    }

    @Around("pointcut()")
    //ProceedingJoinPoint: 正在执行的连接点（切点）
    public Object around(ProceedingJoinPoint p) throws Throwable {
        System.out.println("环绕前增强...");
        Object result = p.proceed();  //切点方法
        System.out.println("环绕后增强...");
        return result;
    }

    @AfterThrowing("MyAspect.pointcut()")
    public void afterThrowing() {
        System.out.println("异常抛出增强...");
    }

    @After("MyAspect.pointcut()")
    public void after() {
        System.out.println("最终增强...");
    }
}
