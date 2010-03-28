package innerclasses;

interface MyIface {
    void doSth();
}

public class AnnonymousClassExample {
    static int static_i;
    int j;
    MyIface myIface = new MyIface() {
        int a;
        
        //cannot use static member variables in any anonymous class 
        //static int b = 0; //will not compile
        
        public void doSth() {
            //not used in static context, can use both static and non-static member variables
            static_i++;
            j++;
        }
    };

    static MyIface myIface2 = new MyIface() {
        int a;
        //cannot use static member variables in any anonymous class 
        //static int b = 0; //will not compile
        public void doSth() {
            // since anonymous class is used in the static context - it
            // cannot access non-static member variables
            static_i++;
            //j++; //will not compile
        }
    };
    
    public static void main(String[] args) {
        MyIface myIface3 = new MyIface() {
            int a;
            //cannot use static member variables in any anonymous class 
            //static int b = 0; //will not compile
            public void doSth() {
                // since anonymous class is used in the static context - it
                // cannot access non-static member variables
                static_i++;
                //j++; //will not compile
            }
        };
    }
    
    public void myMethod(){
        MyIface myIface4 = new MyIface() {
            int a;
            //cannot use static member variables in any anonymous class 
            //static int b = 0; //will not compile
            public void doSth() {
                //not used in static context, can use both static and non-static member variables
                static_i++;
                j++;
            }
        };
        //local variables cannot be nether static nor private/protected/public 
        //static int k = 0; //will not compile
        //private int k = 0; //will not compile
        int k = 0; //ok
        final int kk = 0; //ok
    }

}
