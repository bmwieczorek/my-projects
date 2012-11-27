import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PojoToXmlConverter {

    public Xml convertToXml(Object pojo) {
        return new Xml(pojo);
    }
}

class Xml {
    private final Object pojo;

    public Xml(Object pojo) {
        this.pojo = pojo;
    }

    @Override
    public String toString() {
        return convertToXml(pojo);
    }

    private String convertToXml(Object text) {
        System.out.println("in xml converter");
        return "<" + text + "/>";
    }

}

public class TestSlf4j {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSlf4j.class);

    public static void main(String[] args) {
        PojoToXmlConverter converter = new PojoToXmlConverter();
        // LOGGER.debug("bla {}", converter.convertToXml("pojo"));
        LOGGER.error("bla {}", converter.convertToXml("pojo"));
    }

}
