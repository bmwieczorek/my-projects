package generics.extension.model.backend;

import generics.extension.model.service.Father;

public class ImFather extends ImPerson {

    private String imName;

    public void setImName(String imName) {
        this.imName = imName;
    }

    public String getImName() {
        return imName;
    }

    public ImFather(Father father) {

    }

    @Override
    public String describe() {
        return null;
    }
}
