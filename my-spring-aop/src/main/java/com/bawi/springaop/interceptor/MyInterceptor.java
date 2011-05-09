package com.bawi.springaop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before");
        Object proceed = invocation.proceed();
        System.out.println("after");
        return proceed;
    }

}
