package com.bawi.visitor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.SystemPropertyUtils;

public class ClassUtils {

    public List<String> getClassNamesInPackage(String packageName) {
        List<String> classNames = new ArrayList<String>();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        int length = ".class".length();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils
                        .resolvePlaceholders(packageName)) + "/*.class";
        Resource[] resources;
        try {
            resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    String filename = resource.getFilename();
                    String classNameWithPackage = packageName + "." + filename.substring(0, filename.length() - length);
                    classNames.add(classNameWithPackage);
                }
            }
        } catch (IOException e) {
            VisitorImpl.LOGGER.error(e.getMessage());
        }
        return classNames;
    }

    public List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (String className : getClassNamesInPackage(packageName)) {
            try {
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            } catch (ClassNotFoundException e) {
                VisitorImpl.LOGGER.error(className);
            }
        }
        return classes;
    }

    public Set<Class<?>> getClassHierarchy(Class<?> clazz) {
        Set<Class<?>> set = new LinkedHashSet<Class<?>>();
        Class<?> c = clazz;
        while (c != Object.class) {
            set.add(c);
            set.addAll(Arrays.asList(c.getInterfaces()));
            c = c.getSuperclass();
        }
        return set;
    }

    Class<?> getClassForType(VisitorImpl visitorImpl, Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }

        if (type instanceof ParameterizedType) {
            return visitorImpl.classUtils.getClassForParametrizedType((ParameterizedType) type);
        }
        return null;
    }

    public List<Method> getMethodsInPackageImplementingInterface(VisitorImpl visitorImpl, String packageName,
            Class<?> iface) {
        List<Method> methods = new ArrayList<Method>();
        List<Class<?>> classesInPackage = getClassesInPackage(packageName);
        for (Class<?> clazz : classesInPackage) {
            Method method = visitorImpl.classUtils.getMethodInClassImplementingInterface(visitorImpl, clazz, iface);
            if (method != null) {
                methods.add(method);
            }
        }
        return methods;
    }

    public List<Method> getMethodsInPackageImplementingInterface(VisitorImpl visitorImpl, Class<?> iface) {
        return getMethodsInPackageImplementingInterface(visitorImpl, iface.getPackage().getName(), iface);
    }

    public Method getMethodInClassImplementingInterface(VisitorImpl visitorImpl, Class<?> clazz, Class<?> iface) {
        if (!iface.isInterface()) {
            throw new IllegalArgumentException(iface + " is not an interface");
        }
        if (isEmpty(clazz.getInterfaces())) {
            return null;
        }
        Method[] ifaceMethods = iface.getDeclaredMethods();
        if (isEmpty(ifaceMethods) || ifaceMethods.length != 1) {
            return null;
        }
        Method ifaceMethod = ifaceMethods[0];
        Class<?> parametryzedType = getClassForType(visitorImpl, clazz.getGenericInterfaces()[0]);

        try {
            return clazz.getMethod(ifaceMethod.getName(), parametryzedType);
        } catch (NoSuchMethodException e) {
            VisitorImpl.LOGGER.error(e.getMessage());
        }
        return null;
    }

    private boolean isEmpty(Object[] interfaces) {
        return interfaces == null || interfaces.length == 0;
    }

    Class<?> getClassForParametrizedType(ParameterizedType p) {
        Type firstActualTypeArg = p.getActualTypeArguments()[0];
        if (firstActualTypeArg instanceof Class<?>) {
            return (Class<?>) firstActualTypeArg;
        }
        if (firstActualTypeArg instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) firstActualTypeArg;
            Type parametrizedFirstActualTypeArg = parameterizedType.getActualTypeArguments()[0];
            if (parametrizedFirstActualTypeArg instanceof Class<?>) {
                return (Class<?>) parametrizedFirstActualTypeArg;
            }
            return (Class<?>) parameterizedType.getRawType();
        }
        return (Class<?>) p.getRawType();

    }

}
