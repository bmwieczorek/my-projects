package com.bawi.camel;

import org.apache.camel.main.Main;

public class MyCamelStandalone {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        //main.addRouteBuilder(new MyRouteBuilder());
        main.addRouteBuilder(new MyFileSplitRouteBuilder());
        main.enableHangupSupport();
        main.run();
    }

}
