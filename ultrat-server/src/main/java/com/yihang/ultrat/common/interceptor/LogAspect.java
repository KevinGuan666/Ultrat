package com.yihang.ultrat.common.interceptor;

import cn.hutool.core.date.StopWatch;
import cn.hutool.json.JSONUtil;
import com.yihang.ultrat.common.domain.dto.RequestInfo;
import com.yihang.ultrat.common.utils.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Around("execution(* com..controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        List<Object> paramList = Stream.of(joinPoint.getArgs())
            .filter(args -> !(args instanceof ServletRequest))
            .filter(args -> !(args instanceof ServletResponse))
            .collect(Collectors.toList());
        RequestInfo requestInfo = RequestHolder.get();
        String userHeaderStr = JSONUtil.toJsonStr(requestInfo);
        String parameterStr = paramList.size() == 1 ? JSONUtil.toJsonStr(paramList.get(0)) : JSONUtil.toJsonStr(paramList);
        if (log.isInfoEnabled()) {
            log.info("[{}][{}]【base:{}】【request:{}】", method, uri, userHeaderStr, parameterStr);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long timeCost = stopWatch.getTotalTimeMillis();
        if (log.isInfoEnabled()) {
            log.info("[{}]【response:{}】[cost:{}ms]", uri, parameterStr, timeCost);
        }
        return result;
    }
}
