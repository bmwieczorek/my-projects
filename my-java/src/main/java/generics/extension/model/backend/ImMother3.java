package generics.extension.model.backend;

import generics.extension.model.service.Mother;

@Convertable3(Mother.class)
public class ImMother3 extends ImPerson {

    private Mother mother;

    private String imName;

    public void setImName(String imName) {
        this.imName = imName;
    }

    public String getImName() {
        return imName;
    }

    public ImMother3(Mother mother) {
        this.mother = mother;
    }

    @Override
    public String describe() {
        return "Created mother with name " + mother.getName() + " and husband " + mother.getHusbandName();
    }

}
