package de.fi.webapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class MyAspect {




    @Before("Pointcuts.personenControllerMethod()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.warn("before advice. Methode = " + joinPoint.getSignature().getName());
    }

    @Before("Pointcuts.personenControllerMethod()")
    public void beforeAdviceSchweineService(JoinPoint joinPoint) {
        log.warn("before advice. Methode = " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "Pointcuts.personenControllerMethod()",returning = "result")
    public void afterReturninAdvice(JoinPoint joinPoint, Object result) {
        System.out.println(result);
        log.warn("after returning advice. Methode = " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))",throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        System.out.println(ex);
        log.warn("after throwing advice. Methode = " + joinPoint.getSignature().getName());
    }

    @After(value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void afterReturninAdvice(JoinPoint joinPoint) {

        log.warn("after advice. Methode = " + joinPoint.getSignature().getName());
    }

    @Around(value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{

       return joinPoint.proceed();
    }
}
