package com.bawi.myhibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AbstractDomainObject {

    private static final long serialVersionUID = 1L;
    private String name;
    private Company company;

    // Hibernate required
    Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "COMPANY_ID", nullable = true)
    @ForeignKey(name = "FK_EMPLOYEE_COMPANY")
    public Company getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return name;
    }

}
