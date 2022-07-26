# Spring-AOP

For this project we create the project using the [Spring Initializr](https://start.spring.io/) adding the aop dependency. 

NOTE: If the dependency is not available, we just need to add the below dependency:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
## Explanation of the Project Structure
The first Configuration that we do is to crete the service and data layer with two classes each.
- The **Data Layer** has the dao classes to return data.
- The **Service Layer** has the business clases of the service

## AOP Terminology

#####Point Cut
THe Point Cut has the next format:

execution(* PACKAGE.**.* *(..))

Where the **PACKAGE** is the package of the method that we want to intercept.

** execution(* com.pasnys.aop.example.service.* *.** (..)) **

#####JoinPoint

JointPoint is the specific interception of the method.

```java
import org.aspectj.lang.JoinPoint;
```
This class has all the details of the intercepted methods.

It can be used in as an argument in the method that has the @Before annotation

```java
    @Before("execution(* com.pasnys.aop.example.service.* .* (..))")
    public void before(JoinPoint joinPoint){
        logger.info("Intercepted Method Calls {}", joinPoint);
    }
```

#####Pointcut

Is the **expression that defines** which methods it will be intercepted by the @Before annotation:

```java
@Before("execution(* com.pasnys.aop.example.service.* .* (..))")
@Before("execution(* com.pasnys.aop.example..* .* (..))")
```

We can specify the packages that we need to intercept.

#####Advice

The advice is the **message** that we want to provide in the method interception

```java
    @Before("execution(* com.pasnys.aop.example..* .* (..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info("Check for user access");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }
```
#####Aspect

The **Aspect** is the combination of the **Pointcut** and the **advise**

```java
@Aspect
@Configuration
public class UserAccessAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Before("execution(* com.pasnys.aop.example.service.* .* (..))")
    @Before("execution(* com.pasnys.aop.example..* .* (..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info("Check for user access");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }
}
```

#####Weaving & Weaver

**Weaving**
Is the process of implementation the AOP around your methods.


**Weaver**
The framework which does the entire logic of making sure that the aspect is in.

##Defining an @Before advice

When we create an Apsect we need to indicate the next:
- The class is an AOP with the **@Aspect** annotation.
- Then the class is a Configuration with the **@Configuration**.
- Last we need to add the @Before annotation along with the **Point Cut** in the method that will perform the intercept.

```java
    @Before("execution(* com.pasnys.aop.example..* .* (..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info("Check for user access");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }
```
##Defining an @After

We create an Aspect @After we need to indicate the next:
- The class that is an AOP need to have the **@Aspect** annotation.
- Then the class is a Configuration with the **@Configuration**.
- Last we need to add the @After annotation along with the **Point Cut** 
- 
```java
    @After(value = "execution(* com.pasnys.aop.example.service.* .* (..))")
    public void after(JoinPoint joinPoint){
        //Advice
        //This way shows the qualified method name
        logger.info("after execution of {} ", joinPoint);
    }
````
##Defining an @AfterReturning advice

We create an Aspect @AfterReturning we need to indicate the next:
- The class that is an AOP need to have the **@Aspect** annotation.
- Then the class is a Configuration with the **@Configuration**.
- Last we need to add the @AfterReturning annotation along with the **Point Cut** & the result value (Object result) in the method that will perform the intercept.

```java
    @Before("execution(* com.pasnys.aop.example..* .* (..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info("Check for user access");
        //This way shows the return type and the qualified method name
        logger.info("User Allowed for execution of  {}", joinPoint);
    }
```

##Defining an @AfterThrowing advice

We create an Aspect @AfterThrowing we need to indicate the next:
- The class that is an AOP need to have the **@Aspect** annotation.
- Then the class is a Configuration with the **@Configuration**.
- Last we need to add the @AfterThrowing annotation along with the **Point Cut** & the exception that will be returned by the method that will perform the intercept.

````java
    @AfterThrowing(
            value = "execution(* com.pasnys.aop.example.service.* .* (..))",
            throwing = "exception")
    public void afterException(JoinPoint joinPoint, Exception exception) throws Exception{
        //Advice
        //This way shows the qualified method name and the exception
        logger.info("{} throw exception {}", joinPoint, exception);
````

##Defining an Around advice

This advice is the used to intercept and control the execution of the method using the class:

````java
import org.aspectj.lang.ProceedingJoinPoint;
````

We create an Aspect @Aroun we need to indicate the next:
- The class that is an AOP need to have the **@Aspect** annotation.
- Then the class is a Configuration with the **@Configuration**.
- Last we need to add the @Around annotation along with the **Point Cut** of the method that will perform the intercept. In this case we use the proceed() method
- This annotation @Around can be used as the @Before and @After annotation was one annotation.

````java
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
````

##Using a Common JointPoint 

We can configure a Common JointPoint instead of creating Pointcut every method

To create a Common JointPoint we need create a class where all the Pointcut will be created:
- Create a method that will handle the Pointcut.
- Then add the annotation **@Poincut**.
- Add the common Pointcut that we will handle by the method
- No we can use the common qualified method name instead of the create the Pointcut every time that we need it.  

```java
@Pointcut("execution(* com.pasnys.aop.example.data.* .* (..))")
public void dataExecution(){

        logger.info("Using the Common JoinPoint Config For the Data Layer Aspects");

    }
```

We can configure a Pointcut for all the Layers that we have in the project using the three options:
- With the **bean names**
- With the **within packages** 
- and using our annotations

### Pointcut Bean names

To create a Pointcut by Bean name we need to create the Pointcut using the bean() and the name or pattern of the beans like *dao 
example: @Pointcut("bean(*dao)")
```Java
@Pointcut("bean(*dao)")
public void bean(){
logger.info("Using the Common JoinPoint Config For the beans that ends with Dao");
}
```

### Pointcut Within package

To create a Pointcut that will be intercept within a package we need to create the Pointcut using the within() and the package
example: @Pointcut("within(com.pasnys.aop.example.data.. * )")

````java
    @Pointcut("within(com.pasnys.aop.example.data..*)")
    public void executionWithWithin(){
        logger.info("Using the the Common JoinPoint Config for the execution With Within");
    }
````

### Pointcut Within package

To create a Pointcut with personalized annotations we need to create the annotation and the Pointcut using the annotation() where we will send the qualified annotation name  
example: @Pointcut("@annotation(com.pasnys.aop.example.aspect.annotation.TrackTime)")

```java

    @Pointcut("@annotation(com.pasnys.aop.example.aspect.annotation.TrackTime)")
    public void trackTimeAnnotation(){
        logger.info("Using the the Common JoinPoint Config for the execution usin the Personalized annotation");
    }
```
The TrackTime annotation can be defined:
```java
package com.pasnys.aop.example.aspect.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Methods
@Target(ElementType.METHOD)
//RunTime
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {


}
```

Remember to change the Pointcuts for the Pointcut that you want to use.


##### Output after the execution of the project

```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=53967:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/cesarpasillas/Documents/MyGithub/AOP-Example/target/classes:/Users/cesarpasillas/.m2/repository/org/springframework/boot/spring-boot-starter/2.7.2/spring-boot-starter-2.7.2.jar:/Users/cesarpasillas/.m2/repository/org/springframework/boot/spring-boot/2.7.2/spring-boot-2.7.2.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-context/5.3.22/spring-context-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-expression/5.3.22/spring-expression-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.2/spring-boot-autoconfigure-2.7.2.jar:/Users/cesarpasillas/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.7.2/spring-boot-starter-logging-2.7.2.jar:/Users/cesarpasillas/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/Users/cesarpasillas/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/Users/cesarpasillas/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.17.2/log4j-to-slf4j-2.17.2.jar:/Users/cesarpasillas/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/cesarpasillas/.m2/repository/org/slf4j/jul-to-slf4j/1.7.36/jul-to-slf4j-1.7.36.jar:/Users/cesarpasillas/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-core/5.3.22/spring-core-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-jcl/5.3.22/spring-jcl-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar:/Users/cesarpasillas/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/Users/cesarpasillas/.m2/repository/org/springframework/boot/spring-boot-starter-aop/2.7.2/spring-boot-starter-aop-2.7.2.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-aop/5.3.22/spring-aop-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/springframework/spring-beans/5.3.22/spring-beans-5.3.22.jar:/Users/cesarpasillas/.m2/repository/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar com.pasnys.aop.example.AopExampleApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2022-07-26 15:22:25.640  INFO 26498 --- [           main] c.p.aop.example.AopExampleApplication    : Starting AopExampleApplication using Java 11.0.11 on MacBook-Pro-de-Cesar.local with PID 26498 (/Users/cesarpasillas/Documents/MyGithub/AOP-Example/target/classes started by cesarpasillas in /Users/cesarpasillas/Documents/MyGithub/AOP-Example)
2022-07-26 15:22:25.643  INFO 26498 --- [           main] c.p.aop.example.AopExampleApplication    : No active profile set, falling back to 1 default profile: "default"
2022-07-26 15:22:26.332  INFO 26498 --- [           main] c.p.aop.example.AopExampleApplication    : Started AopExampleApplication in 1.166 seconds (JVM running for 1.513)
2022-07-26 15:22:26.349  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : Check for user access
2022-07-26 15:22:26.349  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : User Allowed for execution of  execution(String com.pasnys.aop.example.service.UserBussines.requestAcces())
2022-07-26 15:22:26.354  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : Using the beforeUsingCommonJoinPoint
2022-07-26 15:22:26.354  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : User Allowed for execution of  execution(String com.pasnys.aop.example.data.UserDao.login())
2022-07-26 15:22:26.358  INFO 26498 --- [           main] dAspect$$EnhancerBySpringCGLIB$$dd7d1e6d : Time taken by the execution(String com.pasnys.aop.example.data.UserDao.login()) is 4
2022-07-26 15:22:26.359  INFO 26498 --- [           main] dAspect$$EnhancerBySpringCGLIB$$dd7d1e6d : Time taken by the execution(String com.pasnys.aop.example.service.UserBussines.requestAcces()) is 10
2022-07-26 15:22:26.359  INFO 26498 --- [           main] rAspect$$EnhancerBySpringCGLIB$$8ed8f870 : execution(String com.pasnys.aop.example.service.UserBussines.requestAcces()) returned with the value null
2022-07-26 15:22:26.359  INFO 26498 --- [           main] rAspect$$EnhancerBySpringCGLIB$$8ed8f870 : after execution of execution(String com.pasnys.aop.example.service.UserBussines.requestAcces()) 
2022-07-26 15:22:26.359  INFO 26498 --- [           main] ication$$EnhancerBySpringCGLIB$$5da6520f : null
2022-07-26 15:22:26.359  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : Check for user access
2022-07-26 15:22:26.359  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : User Allowed for execution of  execution(String com.pasnys.aop.example.service.ClientBussines.calculateSomething())
2022-07-26 15:22:26.363  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : Using the beforeUsingCommonJoinPoint
2022-07-26 15:22:26.363  INFO 26498 --- [           main] eAspect$$EnhancerBySpringCGLIB$$e25266df : User Allowed for execution of  execution(String com.pasnys.aop.example.data.ClientDao.retrieveSomenthing())
2022-07-26 15:22:26.366  INFO 26498 --- [           main] rAspect$$EnhancerBySpringCGLIB$$8ed8f870 : execution(String com.pasnys.aop.example.service.ClientBussines.calculateSomething()) throw exception Login Refused
2022-07-26 15:22:26.366  INFO 26498 --- [           main] rAspect$$EnhancerBySpringCGLIB$$8ed8f870 : after execution of execution(String com.pasnys.aop.example.service.ClientBussines.calculateSomething()) 
2022-07-26 15:22:26.366  INFO 26498 --- [           main] dAspect$$EnhancerBySpringCGLIB$$dd7d1e6d : Time taken by the execution(void com.pasnys.aop.example.AopExampleApplication.run(String[])) is 26

Process finished with exit code 0
```

