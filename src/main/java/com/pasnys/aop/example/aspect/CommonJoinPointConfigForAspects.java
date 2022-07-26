package com.pasnys.aop.example.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class has the configuration of the Pointcut common for all the classes
 */
public class CommonJoinPointConfigForAspects {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This method defines the Pointcut for the package com.pasnys.aop.example.data, now we need to change the Pointcut
     * for this method using the qualified name: com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.dataExecution()
     */
    @Pointcut("execution(* com.pasnys.aop.example.data.* .* (..))")
    public void dataExecution(){

        logger.info("Using the Common JoinPoint Config For the Data Layer Aspects");

    }

    /**
     * This method defines the Pointcut for the package com.pasnys.aop.example.service, now we need to change the Pointcut
     * for this method using the qualified name: com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.serviceExecution()
     */
    @Pointcut("execution(* com.pasnys.aop.example.service.* .* (..))")
    public void serviceExecution(){

        logger.info("Using the Common JoinPoint Config For Service Layer Aspects");

    }

    /**
     * This method defines the common Pointcut for the package com.pasnys.aop.example.data &
     * com.pasnys.aop.example.service using in the @Pointcut annotation the qualified method name that we want to combine
     * com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.dataExecution() &
     * com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.serviceExecution()
     */
    @Pointcut("com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.dataExecution() && " +
            "com.pasnys.aop.example.aspect.CommonJoinPointConfigForAspects.serviceExecution()")
    public void allExecution(){

        logger.info("Using the Common JoinPoint Config For All Layer Aspects");

    }

    /**
     * This method defines the Pointcut for all the beans that ends with DAO
     */
    @Pointcut("bean(*dao)")
    public void bean(){
        logger.info("Using the Common JoinPoint Config For the beans that ends with Dao");
    }


    /**
     * THis method intercept all the methods that are within the package com.pasnys.aop.example.data
     */
    @Pointcut("within(com.pasnys.aop.example.data..*)")
    public void executionWithWithin(){
        logger.info("Using the the Common JoinPoint Config for the execution With Within");
    }

    /**
     * This method intercept all the methods that uses the Pointcut trackTimeAnnotation, this Pointcut uses the
     * annotation @TrackTime
     */
    @Pointcut("@annotation(com.pasnys.aop.example.aspect.annotation.TrackTime)")
    public void trackTimeAnnotation(){
        logger.info("Using the the Common JoinPoint Config for the execution usin the Personalized annotation");
    }
}
