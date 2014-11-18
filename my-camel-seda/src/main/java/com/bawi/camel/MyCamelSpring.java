package com.bawi.camel;

import org.apache.camel.spring.Main;

public class MyCamelSpring {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setApplicationContextUri("my-camel-spring.xml");
        main.enableHangupSupport();
        main.run();
    }

}
