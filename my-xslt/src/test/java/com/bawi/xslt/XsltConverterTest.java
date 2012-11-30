package com.bawi.xslt;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class XsltConverterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(XsltConverterTest.class);

    @Test
    public void shouldConvert() throws Exception {
        // executeTest(1);
        // executeTest(2);
        executeTest(3);
        // executeTest(4);
        // executeTest(5);
    }

    private void executeTest(int testId) throws TransformerConfigurationException, IOException {
        Resource resource = new ClassPathResource("template" + testId + ".xsl");
        XsltConverter converter = new XsltConverter(resource);
        File file = new ClassPathResource("test" + testId + ".xml").getFile();
        String result = converter.convert(file);
        LOGGER.info("Converted xml:\n{}", result);
    }

}
