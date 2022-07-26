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
