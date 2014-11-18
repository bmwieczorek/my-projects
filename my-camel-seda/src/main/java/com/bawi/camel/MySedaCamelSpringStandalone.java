package com.bawi.camel;

import org.apache.camel.spring.Main;


public class MySedaCamelSpringStandalone {

    public static void main(String[] args) throws Exception {
        
        Main main = new Main();
        main.enableHangupSupport();
        main.setApplicationContextUri("my-seda-camel-spring.xml");
        main.run();
    }

}
