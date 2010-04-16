package javabeans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;

public class Example {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(MyJavaBean.class);
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (int i = 0; i < methodDescriptors.length; i++) {
            System.out.println(methodDescriptors[i].getName());
        }
    }
}
