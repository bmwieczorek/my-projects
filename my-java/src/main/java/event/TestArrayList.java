package event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class A{
	String name;
}

public class TestArrayList {
	public static void main(String[] args) {
		A a = new A();
		a.name = "Bawi";
		A a2 = new A();
		a2.name = "Kasia";
		List<A> list = new ArrayList<A>();
		list.add(a);
		list.add(a);
		List<A> list2 = new ArrayList<A>(list);
		list.add(a2);
		list.removeAll(Arrays.asList(a));
		//list.remove(a2);
		//list2.get(0).name = "SSS";
		for (A a3 : list) {
			System.out.println(a3.name);
		}
		for (A a3 : list2) {
			System.out.println("2:"+a3.name);
		}
		
	}
}
