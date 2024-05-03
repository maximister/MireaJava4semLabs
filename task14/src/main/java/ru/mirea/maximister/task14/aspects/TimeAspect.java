package ru.mirea.maximister.task14.aspects;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class TimeAspect {
    @SneakyThrows
    @Around("execution(* *(..)) && within(ru.mirea.maximister.task14.service..*)")
    public Object logTime(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long time = System.currentTimeMillis() - start;
            log.info(
                    "Method {} from class {} runs {} ms",
                    methodSignature.getName(),
                    methodSignature.getDeclaringType().getSimpleName(),
                    time
            );
        }
    }
}