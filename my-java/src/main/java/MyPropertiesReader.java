import java.io.IOException;
import java.io.InputStream;

public class MyPropertiesReader {
    public static void main(String[] args) throws IOException, InterruptedException {

        // File file = new File("classpath:my.properties");
        // System.out.println(file.getAbsolutePath());
        // char[] buffer = new char[20];
        // new FileReader(file).read(buffer);
        // System.out.println(new String(buffer));
        System.out.println(new MyPropertiesReader().read());
        Thread.sleep(50000);
    }

    private String read() {
        InputStream resourceAsStream = getClass().getResourceAsStream("my.properties");
        byte[] buffer = new byte[20];
        try {
            resourceAsStream.read(buffer);
            return new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
