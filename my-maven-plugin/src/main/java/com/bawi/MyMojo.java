package com.bawi;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Says hello
 * 
 * @goal tell
 * @phase process-sources
 */
public class MyMojo extends AbstractMojo {

    /**
     * My parameter
     * 
     * @parameter expression=${tell.text}
     */
    private String text;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello world " + System.getProperty("text") + text);
    }

}
