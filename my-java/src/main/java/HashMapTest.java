import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class HashMapTest {

    private static final String KEY = "myKey";

    @Test
    public void shoundContainsKeyReturnFalseIfTheKeyIsPresentButValueIsNull() {

        // given
        Map<String, String> map = new HashMap<String, String>();
        map.put(KEY, null);

        // when
        boolean containsKey = map.containsKey(KEY);
        System.out.println(containsKey);

        // then
        Assert.assertFalse(containsKey);

    }
}
