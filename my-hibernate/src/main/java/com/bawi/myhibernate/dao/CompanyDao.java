package com.bawi.myhibernate.dao;

import java.util.List;

import com.bawi.myhibernate.domain.Company;

public interface CompanyDao {

    Company saveOrUpdate(Company company);

    void delete(Company company);

    List<Company> getCompanies();

}
