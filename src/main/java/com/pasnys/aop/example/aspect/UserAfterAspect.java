package com.pasnys.aop.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect to intercept the After Access
 */
@Aspect
@Configuration
public class UserAfterAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to be executed after any method on the package com.pasnys.aop.example.service.
     * The method also can display the method return.
     * @param joinPoint
     * @param result
     */
    @AfterReturning(
            value = "execution(* com.pasnys.aop.example.service.* .* (..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        //Advice
        //This way shows the return type and the qualified method name and teh result
        logger.info("{} returned with the value {}", joinPoint, result);
    }

    /**
     * Method to be executed after any exception is thrown by any method on the package com.pasnys.aop.example.service.
     * @param joinPoint
     * @param exception
     * @throws Exception
     */
    @AfterThrowing(
            value = "execution(* com.pasnys.aop.example.service.* .* (..))",
            throwing = "exception")
    public void afterException(JoinPoint joinPoint, Exception exception) throws Exception{
        //Advice
        //This way shows the qualified method name and the exception

        //If we only send teh exception the exception it will be printed complete with the StackTrace
        //logger.info("{} throw exception {}", joinPoint, exception.getMessage());

        logger.info("{} throw exception {}", joinPoint, exception.getMessage());
    }

    /**
     * Method to be executed after any method on the package com.pasnys.aop.example.service.
     * @param joinPoint
     */
    @After(value = "execution(* com.pasnys.aop.example.service.*.* (..))")
    public void after(JoinPoint joinPoint){
        //Advice
        //This way shows the qualified method name
        logger.info("after execution of {} ", joinPoint);
    }
}
