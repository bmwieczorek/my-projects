
public class ValueTypeExample {
    
    Response invoke(){
        return new Response("a",1,"b");
    }
        
    private static final class Response{
        final String body;
        final int statusCode;
        final String exception;
        Response(String body, int statusCode, String exception) {
            this.body = body;
            this.statusCode = statusCode;
            this.exception = exception;
        }
    }

}
