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
        MyClass.getInstance();

        MyClass.id = 2;
        System.out.println("[TestClassLoader] set static id to " + MyClass.id);
        System.out.println("----------------------------------------------------------------");

        MyClassLoader myClassLoader1 = new MyClassLoader();
        System.out.println("[TestClassLoader] loading class "  + myClassName + " by classloader: " + myClassLoader1);
        Class<?> loadedClass1 = myClassLoader1.loadClass(myClassName);

        MyClassLoader myClassLoader2 = new MyClassLoader();
        System.out.println("[TestClassLoader] loading class "  + myClassName + " by classloader: " + myClassLoader2);
        Class<?> loadedClass2 = myClassLoader2.loadClass(myClassName);

        System.out.println("[TestClassLoader] loaded class: " + loadedClass1 + " by classloader: " + myClassLoader1);
        System.out.println("[TestClassLoader] loaded class: " + loadedClass2 + " by classloader: " + myClassLoader2);
        System.out.println(loadedClass1 == loadedClass2);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("[TestClassLoader] invoking method getInstance on "  + loadedClass1 + " loaded by classloader: " + myClassLoader1);
        Object instance1 = loadedClass1.getMethod("getInstance").invoke(null);
        //Object instance1 = loadedClass1.newInstance(); // calls public non-arg constructor

        System.out.println("***************************");


        System.out.println("[TestClassLoader] invoking method getInstance on "  + loadedClass2 + " loaded by classloader: " + myClassLoader2);
        Object instance2 = loadedClass2.getMethod("getInstance").invoke(null);

        MyClass myClassInstance = (MyClass) instance2; // causes Exception in thread "main" java.lang.ClassCastException:
                                                         //     com.bawi.MyClass cannot be cast to com.bawi.MyClass


    }
}
