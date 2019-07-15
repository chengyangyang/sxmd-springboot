package com.sxmd.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 自定义类的创建实例内容
 *
 * @author cy
 * @date 2019年06月20日 11:39
 * Version 1.0
 */
public class FactoryBeanHelp<T> implements InitializingBean, FactoryBean<T> {

    private String innerClassName;
    private static final Logger log = LoggerFactory.getLogger(FactoryBeanHelp.class);

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    @Override
    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isInterface()) {
            return (T) InterfaceProxy.newInstance(innerClass);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(innerClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setCallback(new MethodInterceptorImpl());
            return (T) enhancer.create();
        }
    }
    @Override
    public Class<?> getObjectType() {
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            log.error("context",e);
        }
        return null;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
  class InterfaceProxy implements InvocationHandler {
      private static final Logger log = LoggerFactory.getLogger(InterfaceProxy.class);
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("ObjectProxy execute:" + method.getName());
        return method.invoke(proxy, args);
    }
    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        InterfaceProxy proxy = new InterfaceProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}
  class MethodInterceptorImpl implements MethodInterceptor {
      private static final Logger log = LoggerFactory.getLogger(MethodInterceptorImpl.class);
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.debug("MethodInterceptorImpl:" + method.getName());
        return methodProxy.invokeSuper(o, objects);
    }
}
