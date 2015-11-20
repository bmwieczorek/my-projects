package out.of.memory;

import java.util.ArrayList;
import java.util.List;

public class TestMemory {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            strings.add("abcdefghijklmnopqrstuwxyz" + String.valueOf(i));
            System.out.println(i);
        }
        System.out.println(strings.size());
        List<String> strings2 = new ArrayList<>(strings); // it does not create again new objects that were created on heap above
                                                          // it only copies values of the references to these objects  
        System.out.println(strings2.size());
    }
}
