package mypckg;

class X {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        X other = (X) obj;
        if (i != other.i) {
            return false;
        }
        return true;
    }

    int i;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        return result;
    }

    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj) {
    // return true;
    // }
    // if (obj == null) {
    // return false;
    // }
    // if (!(obj instanceof X)) {
    // return false;
    // }
    // X other = (X) obj;
    // if (i != other.i) {
    // return false;
    // }
    // return true;
    // }

}

class Y extends X {

}

public class Equals {
    public static void main(String[] args) {
        X x = new X();
        x.i = 1;
        X y = new Y();
        y.i = 1;
        // System.out.println(x.equals(y));
        System.out.println(x.equals(y) == (x.getClass() == y.getClass()));

    }

}
