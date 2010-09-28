package com.bawi.myhibernate.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;

@Table(name = "PERSON")
@Entity
@Proxy(lazy = false)
@SuppressWarnings("serial")
public class Person implements Serializable {
	

	/**
     * unique identifier
     */
    private Long id;
    
    /**
     * version for optimistic locking
     */
    private transient int version = -1;

    
    private String name;
    
    public Person(){
    	
    }
    
	@Column(name = "NAME")
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Version
    @Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
    

    
    
}
