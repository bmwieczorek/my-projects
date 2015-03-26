package com.bawi.dozer;

import java.util.List;

public class SourceData {

    public static class Status {
        private Boolean married;

        public void setMarried(Boolean married) {
            this.married = married;
        }

        public Boolean isMarried() {
            return married;
        }
    }

    public static class Child {
        private String name;

        public Child(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private int age;

    private String sourceName;

    private List<Child> children;
    
    private Status status;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}