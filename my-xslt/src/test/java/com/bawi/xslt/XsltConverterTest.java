package com.bawi.xslt;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class XsltConverterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(XsltConverterTest.class);

    @Test
    public void shouldConvert() throws Exception {
        Resource resource = new ClassPathResource("template1.xsl");
        XsltConverter converter = new XsltConverter(resource);
        File file = new ClassPathResource("test1.xml").getFile();
        String result = converter.convert(file);
        LOGGER.info("Converted xml:\n{}", result);
    }

    @Test
    public void shouldConvert2() throws Exception {
        Resource resource = new ClassPathResource("template2.xsl");
        XsltConverter converter = new XsltConverter(resource);
        File file = new ClassPathResource("test2.xml").getFile();
        String result = converter.convert(file);
        LOGGER.info("Converted xml:\n{}", result);
    }

}
