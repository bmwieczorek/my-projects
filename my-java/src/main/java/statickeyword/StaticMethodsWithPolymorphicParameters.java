package statickeyword;

class Drink {
}

class Wine extends Drink {
}

class Tea extends Drink {
}

public class StaticMethodsWithPolymorphicParameters {
    static void print( Drink drink) {
        System.out.println("drink");
    }

    /* */
    static void print(Wine wine) {
        System.out.println("wine");
    }

    static void print(Tea tea) {
        System.out.println("tea");
    }

    /* */

    public static void main(String[] args) {
        print(new Drink());
        print(new Wine());
        print(new Tea());
    }

}
