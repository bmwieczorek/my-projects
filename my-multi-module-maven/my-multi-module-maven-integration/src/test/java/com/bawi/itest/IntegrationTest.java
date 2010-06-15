package com.bawi.itest;

import java.io.IOException;

import org.junit.Test;

import com.bawi.WarMain;
import com.bawi.WarTest;

public class IntegrationTest {

    @Test
    public void test() {
        Thread launchingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    WarMain.main(null);
                    WarTest.main(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        launchingThread.start();
    }
}
