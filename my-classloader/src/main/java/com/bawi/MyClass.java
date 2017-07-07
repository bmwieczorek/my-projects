package com.bawi;

public class MyClass {

    static {
        System.out.println("[MyClass] called static initialization block");


         /*
         System.loadLibrary causes:
         Exception in thread "main" java.lang.UnsatisfiedLinkError: Native Library /some-path/lib/libvibesimplejava.jnilib already loaded in another classloader
            at java.lang.ClassLoader.loadLibrary0(ClassLoader.java:1907)
         */

        String libFilePath = "/some-path/lib/libvibesimplejava.jnilib";
        System.out.println("loading: " + libFilePath );
        System.load(libFilePath);
        System.out.println("loaded");


    }

    public static int id;

    public MyClass() {
        System.out.println("[MyClass] called public constructor");
    }

    public static MyClass getInstance(){
        System.out.println("[MyClass] called static getInstance method, id = " + id);
        // comment System.load to see each class initialized non-shared id
        //return new MyClass();
        return Holder.INSTANCE;
    }

    static class Holder {
        static MyClass INSTANCE = new MyClass();
    }

}
