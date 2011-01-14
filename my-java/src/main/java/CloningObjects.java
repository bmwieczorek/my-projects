public class CloningObjects implements Cloneable {

    private final String foo = "Boiled Eggs";

    public static void main(String[] args) throws Exception {
        CloningObjects aClone = new CloningObjects();
        CloningObjects anotherClone = (CloningObjects) aClone.clone();
        System.out.println(anotherClone.toString());
    }

    @Override
    public String toString() {
        return foo;
    }
}