
public class RunProgramWithVMArguments {

	// run with VM arguments -Dimie=ania
    public static void main(String[] args) {
        String imie = System.getProperty("imie");
        System.out.println("imie="+imie);
    }

}
