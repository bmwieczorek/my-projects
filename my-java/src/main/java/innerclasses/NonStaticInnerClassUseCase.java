package innerclasses;

interface Service {
    void doSth();
}

public class NonStaticInnerClassUseCase {

    // return the implementation of the interface via public access method2
    public Service service() {
        return new ServiceImpl();
    }

    // private (non) static inner class (implementation of interface)
    // private static class ServiceImpl implements Service {
    // or
    private static class ServiceImpl implements Service {
        public void doSth() {
            System.out.println("Do the stuff");
        }
    }

    public static void main(String[] args) {
        NonStaticInnerClassUseCase nonStaticInnerClassUseCase = new NonStaticInnerClassUseCase();
        Service service = nonStaticInnerClassUseCase.service();
        service.doSth();
    }
}
