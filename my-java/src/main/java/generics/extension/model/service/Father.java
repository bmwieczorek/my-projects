package generics.extension.model.service;

public class Father extends Person {

    private String name;
    private String wifeName;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

}
