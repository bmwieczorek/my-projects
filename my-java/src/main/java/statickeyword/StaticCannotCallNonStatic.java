package statickeyword;

public class StaticCannotCallNonStatic {

    int i = 10;

    static void fstatic() {

        // not possible
        // finstance();
        // i = 20;

        // not possible
        // this.finstance();
        // this.i = 20;

        // only this way
        StaticCannotCallNonStatic c = new StaticCannotCallNonStatic();
        c.finstance();
        c.i = 20;
    }

    void finstance() {

        // instance method can always call static method
        fstatic();
    }

}
