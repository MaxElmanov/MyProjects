package ru.springAOP.loggings;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

    @Pointcut("execution(* ru.springAOP.objects.Manager.*(..)) && @annotation(ru.springAOP.MyAnnotation.ShowTime)")
    private void allMethods(){}

    @Around("allMethods()")
    public Object watchTime(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();

        System.out.println("method begin: "+ joinPoint.getSignature());

        Object output = null;
        try{
            output = joinPoint.proceed(new Object[]{"C:\\Users\\MaxNick\\Desktop"});
        }
        catch(Throwable e){
            e.printStackTrace();
        }
            long time = System.currentTimeMillis() - start;

            System.out.println("method end: " + joinPoint.getSignature().toShortString() + " time = " + time + " ms");

        return output;
    }


    @AfterReturning(pointcut = "allMethods() && execution(java.util.Map *(String)) && args(path)", returning = "map")
    public void printMap(Map map, String path){

        System.out.println("Print MAP begin");
        System.out.println("Folder: "+path);

        for (Object key : map.keySet()) {
            System.out.println("key= " + key + " - " + map.get(key));
        }

        System.out.println("Print MAP end>>>>>");
    }

    @AfterReturning(pointcut = "allMethods() && execution(java.util.Set *(String)) && args(path)", returning = "set")
    public void printSet(Set set, String path){

        System.out.println("Print SET begin");
        System.out.println("Folder: "+path);

        for (Object object : set) {
            System.out.println(object);
        }

        System.out.println("Print SET end>>>>>");
    }
}
