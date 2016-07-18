package com.geekcap.javaworld.sparkexample;

/**
 * Created by SG0212148 on 7/18/2016.
 */

import org.apache.spark.sql.SQLContext;

import java.io.Serializable;

public class SomeClass implements Serializable{

    SQLContext sqlContext;
    public SomeClass(SQLContext sqlContext) {
        this.sqlContext = sqlContext;
    }
    public void doSomething(String path) {

    }

}