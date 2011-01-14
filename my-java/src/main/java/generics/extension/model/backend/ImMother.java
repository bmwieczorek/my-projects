package generics.extension.model.backend;

import generics.extension.model.service.Mother;

@Convertable(Mother.class)
public class ImMother extends ImPerson {
    private Mother mother;

    private String imName;

    @Override
    public void setImName(String imName) {
        this.imName = imName;
    }

    @Override
    public String getImName() {
        return imName;
    }

    public ImMother(Mother mother) {
        this.mother = mother;
    }

    @Override
    public String describe() {
        return "Created mother with name " + mother.getName() + " and husband " + mother.getHusbandName();
    }
}
