package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Iface {
    void myMethod(String beer);
}

class Impl implements Iface {
    public void myMethod(String beer) {
        System.out.println("Drink " + beer + " and enjoy!");
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        args[0] = args[0] + "&Zywiec";
        return method.invoke(proxied, args);
    }

}

public class DynamicProxyExample {
    public static void main(String[] args) {
        final Iface real = new Impl();
        real.myMethod("Heineken");
        Iface proxy = (Iface) Proxy.newProxyInstance(Iface.class.getClassLoader(),
                new Class[] { Iface.class }, new DynamicProxyHandler(real));
        proxy.myMethod("Heineken");
        // the same but inner class
        Iface proxy2 = (Iface) Proxy.newProxyInstance(Iface.class.getClassLoader(),
                new Class[] { Iface.class }, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        args[0] = args[0] + "&Zywiec";
                        Object invoke = method.invoke(real, args);
                        System.out.println("after");
                        return invoke;
                    }
                });
        proxy2.myMethod("Heineken");
    }

}
