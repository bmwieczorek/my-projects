public class Cloner implements Cloneable{

    private final String foo = "Boiled Eggs";

    public static void main(String[] args) throws Exception {
        Cloner aClone = new Cloner();
        Cloner anotherClone = (Cloner) aClone.clone();
        System.out.println(anotherClone.toString());
    }

    public String toString() {
        return foo;
    }
}