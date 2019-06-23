package com.test.spring.classes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Song {

    @Pointcut("execution(* com.test.spring.classes.BraveKnight.divideAbyB(int, int))")
    public void preformance(){}

    @Before("preformance()")
    public void SingSongBefore(){
        System.out.println("Knight will divide number! (before)");
    }

    @After("preformance()")
    public void SingSongAfter(){
        System.out.println("Knight divided number! (after)");
    }

    @AfterThrowing("preformance()")
    public void SingSongThrowing(){
        System.out.println("Knight didn't divide number! (throwing)");
    }

    @Around("preformance()")
    public int watchTime(ProceedingJoinPoint joinPoint) throws Throwable {
        SingSongBefore();
        float start = System.currentTimeMillis();

        joinPoint.proceed(joinPoint.getArgs());

        SingSongAfter();
        float result = System.currentTimeMillis() - start;
        System.out.println("time went " + result);
        return 1;
    }
}
