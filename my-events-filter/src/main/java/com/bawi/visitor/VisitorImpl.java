package com.bawi.visitor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisitorImpl {

    static final Logger LOGGER = LoggerFactory.getLogger(VisitorImpl.class);
    public final ClassUtils classUtils;

    public VisitorImpl(ClassUtils classUtils) {
        this.classUtils = classUtils;
    }

    public void visitObject(Object arg) {
        Map<Method, Class<?>> methodToClassMapping = getMethodToClassMapping(Visitor.class);
        Map<Class<?>, Method> argumentClassToMethodMapping = getArgumentClassToMethodMapping(Visitor.class);
        try {
            Method method = getVisitorMethodBestMatchingArgument(arg, argumentClassToMethodMapping);
            Object visitor = methodToClassMapping.get(method).newInstance();
            method.invoke(visitor, arg);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Method getVisitorMethodBestMatchingArgument(Object argument, Map<Class<?>, Method> mapping) {
        for (Class<?> clazz : classUtils.getClassHierarchy(argument.getClass())) {
            if (mapping.containsKey(clazz)) {
                return mapping.get(clazz);
            }
        }
        return null;
    }

    public Map<Class<?>, Method> getArgumentClassToMethodMapping(Class<?> iface) {
        Map<Class<?>, Method> mapping = new HashMap<Class<?>, Method>();
        for (Method method : classUtils.getMethodsInPackageImplementingInterface(this, iface)) {
            Class<?> genericClass = classUtils.getClassForType(this, method.getGenericParameterTypes()[0]);
            mapping.put(genericClass, method);
        }
        return mapping;
    }

    public Map<Method, Class<?>> getMethodToClassMapping(Class<?> iface) {
        Map<Method, Class<?>> mapping = new HashMap<Method, Class<?>>();
        List<Class<?>> classesInPackage = classUtils.getClassesInPackage(iface.getPackage().getName());
        for (Class<?> clazz : classesInPackage) {
            Method method = classUtils.getMethodInClassImplementingInterface(this, clazz, iface);
            if (method != null) {
                mapping.put(method, clazz);
            }
        }
        return mapping;
    }

}
