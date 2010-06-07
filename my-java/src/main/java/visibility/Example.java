package visibility;

import visibility.passenger.Toyota;

public class Example {
    public static void main(String[] args) {
        new Example().print();
    }

    private void print() {
        @SuppressWarnings("unused")
        Toyota toyota = new Toyota();
        // toyota.price // price not visible
    }

}
