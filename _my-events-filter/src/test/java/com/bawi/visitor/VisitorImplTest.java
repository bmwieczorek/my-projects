package com.bawi.visitor;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class VisitorImplTest {

    private final ClassUtils classUtils = new ClassUtils();
    private final ClassUtilsTest utilsTest;

    @Test
    public void should() {
        // given
        Collection<Object> data = new ArrayList<Object>();
        data.add(new Cat("a"));
        data.add(Arrays.asList(new Cat("b"), new Cat("c")));
        VisitorImpl visitor = new VisitorImpl(classUtils);

        // when
        visitor.visitObject(data);

        // then

    }

    @Test
    public void shouldFindMethodImplementingInterface() {
        // given
        VisitorImpl visitor = new VisitorImpl(classUtils);

        // when
        Method method = visitor.classUtils.getMethodInClassImplementingInterface(visitor, CatVisitor.class,
                Visitor.class);

        // then
        assertEquals("visit", method.getName());
        assertEquals(1, method.getParameterTypes().length);
        assertEquals(Cat.class, method.getParameterTypes()[0]);

    }

    @Test
    public void shouldGetObjectClassesHierarchy() {
        // given
        VisitorImpl visitor = new VisitorImpl(classUtils);

        // when
        Set<Class<?>> classHierarchy = visitor.classUtils.getClassHierarchy(ArrayList.class);

        // then
        List<Class<?>> list = new ArrayList<Class<?>>(classHierarchy);
        assertEquals(ArrayList.class, list.get(0));
        assertEquals(Collection.class, list.get(list.size() - 1));
    }

    @Test
    public void shouldFindMethodsImplementingInterfaceInPackage() {
        // given
        VisitorImpl visitor = new VisitorImpl(classUtils);

        // when
        List<Method> methods = visitor.classUtils.getMethodsInPackageImplementingInterface(visitor, Visitor.class);

        // then
        assertEquals(2, methods.size());
        assertEquals(Cat.class, methods.get(0).getGenericParameterTypes()[0]);
        assertEquals(Collection.class, getType(methods.get(1)));

    }

    private Class<?> getType(Method method) {
        ParameterizedType type2 = (ParameterizedType) method.getGenericParameterTypes()[0];
        return (Class<?>) type2.getRawType();
    }
}
