package com.bawi.spring.boot.version;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
public class ApplicationVersionController {

    @RequestMapping("/build/version")
    public Properties getVersion() {
        return readPropertiesFile("/build.properties");
    }

    private Properties readPropertiesFile(String resource) {
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream(resource)){
            props.load(is);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return props;
        }
    }

}
