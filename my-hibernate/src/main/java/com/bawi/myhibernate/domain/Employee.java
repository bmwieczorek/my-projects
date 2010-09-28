package com.bawi.myhibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "EMPLOYEE")
@SuppressWarnings("serial")
public class Employee extends AbstractDomainObject {

    private String name;

    Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Company company;

    public void setCompany(Company company) {
        this.company = company;
    }

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "COMPANY_ID", nullable = true)
    @ForeignKey(name = "FK_COMPANY_TO_EMPLOYEE")
    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return name;
    }

}
