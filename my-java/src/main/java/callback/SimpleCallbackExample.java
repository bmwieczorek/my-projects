package callback;

interface CallbackHandler {
    void doInCallback();
}

class CallbackTemplate {
    void execute(CallbackHandler callbackHandler) {
        System.out.println("Opening resources");
        callbackHandler.doInCallback();
        System.out.println("Closing resources");
    }
}

public class SimpleCallbackExample {

    public static void main(String[] args) {
        CallbackTemplate callbackTemplate = new CallbackTemplate();
        callbackTemplate.execute(new CallbackHandler() {
            @Override
            public void doInCallback() {
                System.out.println("Doing real job");
            }
        });
    }

}
