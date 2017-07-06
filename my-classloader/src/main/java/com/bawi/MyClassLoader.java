package com.bawi;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    private static String MY_CLASS_NAME = MyClass.class.getName();

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        if (!className.equals(MY_CLASS_NAME)) { // load Object class and other
            System.out.println("[MyClassLoader] delegating loadClass for " + className + " to parent classloader");
            return super.loadClass(className);
        }
        try {
            String myClassResource = MY_CLASS_NAME.replace(".", "/") + ".class";
            System.out.println("[MyClassLoader] getting stream for resource: " + myClassResource);
            InputStream in = ClassLoader.getSystemResourceAsStream(myClassResource);
            byte[] bytes = new byte[10000];
            int len  = in.read(bytes);
            in.close();
            System.out.println("[MyClassLoader] calling defineClass for bytes from: " + myClassResource);
            return defineClass(className, bytes, 0, len);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
