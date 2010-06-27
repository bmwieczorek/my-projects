package com.bawi.myspringbeans.dao.impl;

import com.bawi.myspringbeans.dao.PersonDao;

public class PersonDaoImpl implements PersonDao {

    private int counter;

    @Override
    public int getCounter() {
        return counter;
    }
}
