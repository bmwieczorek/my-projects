
public class Comarch {
    enum MY_ENUM {AE,BE};

    void myMethod() {
        class Ania {
        }
        new Ania();
    }

    void my() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
        } catch (Exception e) {
        } catch (Throwable t) {
        }
    }
    
   

    public static void main(String[] args) {
        System.out.println((new RuntimeException()) instanceof Exception);

        //Integer i = Integer.valueOf(1);
        // Long i = Long.valueOf(1); //not for long
        
        short i = 1;
        int b = i;
        char cc = 's';
        int c = cc;
        //int i = 1;
        //MY_ENUM i = MY_ENUM.AE;
        switch (c) {
        //case 1: // int ok
        //case AE:  // enum ok
        case 's':
            break;
        default:
            break;
        }

    }
}
