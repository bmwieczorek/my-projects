package generics.extension.model.backend;

public class ImFather extends ImPerson {

    private String imName;

    @Override
    public void setImName(String imName) {
        this.imName = imName;
    }

    @Override
    public String getImName() {
        return imName;
    }

    @Override
    public String describe() {
        return null;
    }
}
