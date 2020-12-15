package com.smart.lock.annoation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(-2)
public @interface DistributedLock {
    String prefix() default "";

    String suffix() default "";

    int waitTime() default 0;

    int leaseTime() default 0;
}
