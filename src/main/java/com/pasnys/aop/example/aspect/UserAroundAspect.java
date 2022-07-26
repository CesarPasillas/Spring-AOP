package com.pasnys.aop.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Aspect
@Configuration
public class UserAroundAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * For this method we use a org.aspectj.lang.ProceedingJoinPoint that allows the method to be intercepted and
     * control the excecition of the intercepted method.
     * In the example we calculate the time taken of the execution of the intercepted method.
     * @param joinPoint
     */
    @Around("execution(* com.pasnys.aop.example..* .* (..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        //start time
        long startTime = System.currentTimeMillis();

        //allow the Execution of the method
        joinPoint.proceed();

        //end time
        long timeTaken = System.currentTimeMillis() - startTime;

        logger.info("Time taken by the {} is {}", joinPoint, timeTaken);
    }

}
