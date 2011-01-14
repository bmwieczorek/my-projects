import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommaSeparatedPropertiesReader {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = CommaSeparatedPropertiesReader.class.getClassLoader().getResourceAsStream(
                "my.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String property = properties.getProperty("letters");
        String[] split = property.split(",");
        for (String string : split) {
            System.out.println(string);
        }
    }
}
