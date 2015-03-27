package collections;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class HashSetOrderTest {
    private static class Holder {
        private Integer value;
        public Holder(Integer value) {
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
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
            Holder other = (Holder) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }
        
    }

    @Test
    public void testJava() throws Exception {
        Set<Holder> valuesSet = new HashSet<Holder>();
        valuesSet.add(new Holder(1));
        valuesSet.add(new Holder(2));
        valuesSet.add(new Holder(3));
        valuesSet.add(new Holder(4));
        StringBuilder builder = new StringBuilder();
        for (Holder holder : valuesSet) {
            builder.append(holder.getValue());
        }
        System.out.println(builder.toString());
    }
}
