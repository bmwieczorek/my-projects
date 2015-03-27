package com.bawi.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatVisitor implements Visitor<Cat> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatVisitor.class);

    @Override
    public void visit(Cat cat) {
        LOGGER.info("Visiting cat " + cat);
    }

}
