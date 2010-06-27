package com.bawi.myspringbeans.service;

import org.apache.log4j.Logger;

import com.bawi.myspringbeans.dao.PersonDao;

public class MyManagerService {

    private static final Logger logger = Logger.getLogger(MyManagerService.class);

    private final PersonDao personDao;

    public MyManagerService(PersonDao personDao) {
        this.personDao = personDao;
        logger.debug(getClass().getName() + ":" + personDao + ":" + personDao.getCounter());
    }

    public PersonDao getPersonDao() {
        return personDao;
    }
}
