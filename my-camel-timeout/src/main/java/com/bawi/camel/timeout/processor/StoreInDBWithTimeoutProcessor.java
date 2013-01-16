package com.bawi.camel.timeout.processor;

import java.util.concurrent.ExecutorService;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreInDBWithTimeoutProcessor extends AbstractExecuteWithTimeoutProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreInDBWithTimeoutProcessor.class);

    private final DBDao dBDao;

    public StoreInDBWithTimeoutProcessor(ExecutorService executorService, DBDao dBDao) {
        super(executorService);
        this.dBDao = dBDao;
    }

    @Override
    public Exchange doInProcess(Exchange exchange) throws Exception {
        BusinessObject businessObject = exchange.getIn().getBody(BusinessObject.class);
        dBDao.insert(businessObject);
        //Thread.sleep(5000);
        LOGGER.info("Document(s) have been saved.");
        return null;
    }

}
