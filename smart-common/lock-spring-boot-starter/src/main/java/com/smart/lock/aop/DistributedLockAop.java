package com.smart.lock.aop;

import com.smart.lock.annoation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


@Aspect
@Slf4j
public class DistributedLockAop {
    @Resource
    Redisson redisson;

    @Pointcut("@annotation(com.smart.lock.annoation.DistributedLock)")
    public void pointcut() {
    }

    /**
     * 通过反射获取方法的注解信息
     * @param joinPoint
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        String prefix = distributedLock.prefix();
        String suffix = distributedLock.suffix();
        String lockName;
        if (!StringUtils.isEmpty(prefix) && !StringUtils.isEmpty(suffix)) {
            lockName = String.format("%s:%s", prefix, suffix);
        } else if (!StringUtils.isEmpty(prefix)) {
            lockName = prefix;
        } else {
            lockName = String.format("%s:%s", joinPoint.getTarget().getClass().getName(), method.getName());
        }

        RLock lock = redisson.getLock(lockName);

//        int waitTime = distributedLock.waitTime();
        int leaseTime = distributedLock.leaseTime();
        if (leaseTime > 0) {
            lock.lock(leaseTime, TimeUnit.SECONDS);
        } else {
            lock.lock();
        }
        try {
            return joinPoint.proceed();
        } finally {
            lock.unlock();
        }
    }


}
