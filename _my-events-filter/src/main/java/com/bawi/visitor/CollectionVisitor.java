package com.bawi.visitor;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionVisitor implements Visitor<Collection<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionVisitor.class);

    @Override
    public void visit(Collection<?> collection) {
        LOGGER.info("Visiting collection " + collection);
    }

}
