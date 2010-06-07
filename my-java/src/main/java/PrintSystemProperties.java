import java.util.Properties;

public class PrintSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for (String property : properties.stringPropertyNames()) {
            System.out.println(property + "=" + properties.getProperty(property));
        }
    }
}
