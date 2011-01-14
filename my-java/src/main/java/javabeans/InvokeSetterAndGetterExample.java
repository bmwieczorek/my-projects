package javabeans;

class MyBean {
    private String value = "ania";

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

public class InvokeSetterAndGetterExample {
    public static void main(String[] args) {
        makeValueNull(new MyBean());
    }

    static public void makeValueNull(Object o) {
        // o is an object of an unknown class that follows
        // JavaBean naming conventions

        // We want to set the objects "Value" property to null.
        try {
            o.getClass().getMethod("setValue", new Class[] { String.class }).invoke(o, new Object[] { "Kasia" });
            System.out.println(((MyBean) o).getValue());
        } catch (Exception e) {
            System.out.println("Couldn't set the value");
        }
    }
}
