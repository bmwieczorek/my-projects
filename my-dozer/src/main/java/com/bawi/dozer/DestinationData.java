package com.bawi.dozer;


public class DestinationData {
    public static class Status {
        private Boolean married;

        public void setMarried(Boolean married) {
            this.married = married;
        }

        public Boolean isMarried() {
            return married;
        }
    }

    public static class Children {
        private String names;

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        @Override
        public String toString() {
            return "Children [names=" + names + "]";
        }
    }

    private int age;
    private String destinationName;
    private Children children;
    private Status status;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }


    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }
}