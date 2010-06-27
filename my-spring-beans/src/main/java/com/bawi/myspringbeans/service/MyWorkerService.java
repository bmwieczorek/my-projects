package com.bawi.myspringbeans.service;

import org.apache.log4j.Logger;

import com.bawi.myspringbeans.dao.PersonDao;

public class MyWorkerService {

    private static final Logger logger = Logger.getLogger(MyWorkerService.class);

    private final PersonDao personDao;

    public MyWorkerService(PersonDao personDao) {
        this.personDao = personDao;
        logger.debug(getClass().getName() + ":" + personDao + ":" + personDao.getCounter());
    }

    public PersonDao getPersonDao() {
        return personDao;
    }
}
