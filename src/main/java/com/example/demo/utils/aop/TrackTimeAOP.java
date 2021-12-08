package com.example.demo.utils.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Aspect
@Configuration
public class TrackTimeAOP {
    private static final Logger LOGGER = Logger.getLogger(TrackTimeAOP.class.getName());

    @Pointcut("execution(* com.example.demo.services.impl.AnimalServiceImpl.getAllSearched(..))")
    public void track(){}


    @Around("track()")
    public Object trackLatency(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();


        LOGGER.warning("Method process time was "+stopWatch.getTime()+" milliseconds");


        return result;
    }

}
