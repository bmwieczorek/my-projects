package collections;

import java.util.ArrayList;
import java.util.List;

public class MyObject {
    String s;

    public MyObject() {
    }

    public MyObject(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals used");
        return this.s == ((MyObject)obj).s ? true : false;
    }

    @Override
    public int hashCode() {
        System.out.println("hash used");
        //return s.hashCode();
        return "ania".hashCode();
    }

//    @Override
//    public String toString() {
//        return this.s;
//    }

    public static void main(String[] args) {
        List<MyObject> arrayList = new ArrayList<MyObject>();

        arrayList.add(new MyObject("a"));
        arrayList.add(new MyObject("b"));
        arrayList.add(new MyObject("c"));
        arrayList.add(new MyObject("b"));
        arrayList.add(new MyObject("d"));
        arrayList.remove(new MyObject("b"));
        System.out.println("aa");
        System.out.println(new MyObject("a").equals(new MyObject("b")));
        //        
        // Set<MyObject> set = new HashSet<MyObject>();
        // set.add(myObject);
        // set.add(new MyObject());
        // set.remove(myObject);
        //        
        // Map<String,MyObject> map = new HashMap<String, MyObject>();
        // map.put("1", myObject);
        // Map<MyObject,String> map2 = new HashMap<MyObject, String>();
        // map2.put(myObject, "1");
        // map2.put(new MyObject(), "2");
        // map2.get(myObject);
    }
}
