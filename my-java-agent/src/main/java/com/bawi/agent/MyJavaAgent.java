package com.bawi.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyJavaAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
                if ("com/bawi/application/MyApplication".equals(s)) {
                    try {
                        ClassPool cp = ClassPool.getDefault();
                        CtClass cc = cp.get("com.bawi.application.MyApplication");
                        CtMethod m = cc.getDeclaredMethod("getHelloMessage");
                        m.addLocalVariable("elapsedTime", CtClass.longType);
                        m.insertBefore("name=\"Bar instead of Foo\"; " +
                                "elapsedTime = System.currentTimeMillis();");
                        m.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                                + "System.out.println(\"[MyJavaAgent] Method Executed in ms: \" + elapsedTime);}");
                        byte[] byteCode = cc.toBytecode();
                        cc.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                return null;
            }
        });
    }
}
