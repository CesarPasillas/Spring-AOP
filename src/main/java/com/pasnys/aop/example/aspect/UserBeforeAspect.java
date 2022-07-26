package com.pasnys.aop.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect to intercept the User Access using the @Before annotation.
 * we indicate the before to call any method of the package com.pasnys.aop.example.service
 */
@Aspect
@Configuration
public class UserBeforeAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to be executed before any method on the package com.pasnys.aop.example.service
     * @param joinPoint
     */
    @Before("execution(* com.pasnys.aop.example.service.* .* (..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info("Check for user access");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }


    @Before("com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.dataExecution()")
    public void beforeUsingCommonJoinPoint(JoinPoint joinPoint){
        //Advice
        logger.info("Using the beforeUsingCommonJoinPoint");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }
}
