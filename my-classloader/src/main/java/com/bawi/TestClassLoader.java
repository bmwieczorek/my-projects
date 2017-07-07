package com.bawi;

import java.lang.reflect.InvocationTargetException;

public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        System.out.println("[TestClassLoader] started main");
        System.out.println("[TestClassLoader] current classloader: " + TestClassLoader.class.getClassLoader());


        String myClassName = MyClass.class.getName();
        System.out.println("[TestClassLoader] getting value of static field id for " + myClassName);
        System.out.println(MyClass.id);

        System.out.println("[TestClassLoader] calling static getInstance on " + myClassName);
        MyClass instance01 = MyClass.getInstance("/some-path/lib/libvibesimplejava.jnilib");
        System.out.println("[TestClassLoader] instance01: " + instance01);

        MyClass.id = 2;
        System.out.println("[TestClassLoader] set static id to " + MyClass.id);

        System.out.println("[TestClassLoader] calling static getInstance on " + myClassName);
        MyClass instance02 = MyClass.getInstance("/some-path/lib/libvibesimplejava.jnilib"); // note shared static id
        System.out.println("[TestClassLoader] instance02: " + instance02);

        System.out.println(instance01 == instance02); // the same instance since loaded by the same classloader

        System.out.println("================================================================");

        Class<?> loadedClass03 = Class.forName(myClassName);
        System.out.println("[TestClassLoader] Class.forName loaded class " + loadedClass03 + " by classloader: " + loadedClass03.getClassLoader());
        Object instance03 = loadedClass03.newInstance();// calls public non-arg constructor
        System.out.println("[TestClassLoader] instance03: " + instance03);
        System.out.println(instance01 == instance03); // different instance since created by constructor

        System.out.println("----------------------------------------------------------------");

        MyClassLoader myClassLoader1 = new MyClassLoader();
        System.out.println("[TestClassLoader] loading class "  + myClassName + " by classloader: " + myClassLoader1);
        Class<?> myCLLoadedClass1 = myClassLoader1.loadClass(myClassName);

        MyClassLoader myClassLoader2 = new MyClassLoader();
        System.out.println("[TestClassLoader] loading class "  + myClassName + " by classloader: " + myClassLoader2);
        Class<?> myCLLoadedCLass2 = myClassLoader2.loadClass(myClassName);

        System.out.println("[TestClassLoader] loaded class: " + myCLLoadedClass1 + " by classloader: " + myClassLoader1);
        System.out.println("[TestClassLoader] loaded class: " + myCLLoadedCLass2 + " by classloader: " + myClassLoader2);
        System.out.println(myCLLoadedClass1 == myCLLoadedCLass2);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("[TestClassLoader] invoking method getInstance on "  + myCLLoadedClass1 + " loaded by classloader: " + myClassLoader1);
        Object instance1 = myCLLoadedClass1.getMethod("getInstance", String.class).invoke(null, "/some-path/lib/libvibesimplejava.jnilib");
        System.out.println("[TestClassLoader] instance1: " + instance1);

        System.out.println("***************************");


        System.out.println("[TestClassLoader] invoking method getInstance on "  + myCLLoadedCLass2 + " loaded by classloader: " + myClassLoader2);
        Object instance2 = myCLLoadedCLass2.getMethod("getInstance", String.class).invoke(null, "/some-path/lib/libvibesimplejava.jnilib");
        System.out.println("[TestClassLoader] instance2: " + instance2);

        System.out.println(instance01 == instance1);
        System.out.println(instance1 == instance2);
        System.out.println(instance2 == instance01);

        MyClass myClassInstance = (MyClass) instance2; // causes Exception in thread "main" java.lang.ClassCastException:
                                                         //     com.bawi.MyClass cannot be cast to com.bawi.MyClass

    }
}
