package mock;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Key {

    public Method method;
    public Object[] args;

    public Key(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(args);
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Key other = (Key) obj;
        if (!Arrays.equals(args, other.args))
            return false;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Key [method=" + method + ", args=" + Arrays.toString(args) + "]";
    }

}
