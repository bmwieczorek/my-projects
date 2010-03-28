package encoding;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

public class MyReader {

	public static void main(String[] args) throws IOException {
		String name = "ĄąĘeĆc";
		// String name = "Bartek";
		byte[] bytes = name.getBytes();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		while (bis.available() > 0) {
			int read = bis.read();
			System.out.println((char) read);
		}
		StringReader stringReader = new StringReader(name);
		int j;
		while ((j = stringReader.read()) > 0) {
			System.out.println((char) j);
		}
	}
}
