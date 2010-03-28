package annotations;

import java.lang.reflect.Method;

public class RunExample {

    public static void main(String[] args) {
        detectAnnotations(ClassUsingAnnotations.class);
    }
    
    static void detectAnnotations(Class<?> annotatedClass){
        Method[] declaredMethods = annotatedClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Column annotation = method.getAnnotation(Column.class);
            System.out.println(annotation.toString());
            Constraints constraints = annotation.constraints();
            System.out.println(constraints.toString());
        }
    }
}
