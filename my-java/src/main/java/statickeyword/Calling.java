package statickeyword;

public class Calling {
    
    int i = 10;
    
    static void fstatic() {

        // not possible
        // finstance();
        //i = 20;

        // not possible
        // this.finstance();
        //this.i = 20;
        
        //only this way
        Calling c = new Calling();
        c.finstance();
        c.i = 20;
    }

    void finstance() {

        // instance method can always call static method
        fstatic();
    }

}
