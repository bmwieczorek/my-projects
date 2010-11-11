package com.bawi.myhibernate.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company extends AbstractDomainObject {

    private String name;
    private List<Employee> employees = new ArrayList<Employee>();

    // hibernate required
    Company() {
    }

    // we must manually set reference from employee to its company
    public Company(String name, List<Employee> employees) {
        this.name = name;
        for (Employee employee : employees) {
            employee.setCompany(this);
            this.employees.add(employee);
        }
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
