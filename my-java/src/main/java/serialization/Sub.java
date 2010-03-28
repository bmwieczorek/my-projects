package serialization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

class Super {
    protected void setOutput(URL u) throws IOException {
        // .. Additional Code not shown ..
    }
}

public class Sub extends Super {
    public void setOutput(URL u) // Line A
           // throws IOException, DigestException // Line B
    { /* .. Additional Code not shown .. */
    }

    private void setOutput(OutputStream o) // Line C
            throws FileNotFoundException // Line D
    { /* .. Additional Code not shown .. */
    }

    public boolean isOutputSet() // Line E
    { /* .. Additional Code not shown .. */
        return true;
    }
}