package exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

interface Printable {
    void print() throws IOException;
}

class Doc1 implements Printable {

    @Override
    public void print() throws IOException { // ok as the same exception
    }
}

class Doc2 implements Printable {
    
    @Override
    public void print() throws FileNotFoundException { // ok as FileNotFoundException extends IOException
    }
}

    // not possible as Exception is not a subclass of IOException
    //public void print() throws Exception { // Exception is not compatible with throws clause in Printable.print()


public class ExceptionMethodSignatureInSubclasses {

}
