package com.bawi.myhibernate.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractDomainObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier
     */
    private Long id;

    /**
     * version for optimistic locking
     */
    private transient int version = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Version
    @Column(name = "VERSION", nullable = false)
    public int getVersion() {
        return version;
    }

    /**
     * Setter for id.
     * 
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter for version.
     * 
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof AbstractDomainObject)) {
            return false;
        }

        AbstractDomainObject that = (AbstractDomainObject) other;

        Long thisId = this.getId();
        Long thatId = that.getId();

        if ((thisId != null) && (thatId != null)) {
            return thisId.equals(thatId);
        }

        return false;
    }

    /**
     * Is the Object transient (not persisted).
     * 
     * @return getId() == null
     */
    @Transient
    public boolean isTransient() {
        return id == null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        /*
         * TODO This is dangerous with generated identifiers!
         * 
         * 
         * 
         * 
         * 
         * http://www.hibernate.org/hib_docs/v3/reference/en/html/persistent-classes
         * .html#persistent-classes-equalshashcode
         */
        if (getId() == null) {
            return super.hashCode();
        }
        return getId().hashCode();
    }

}
