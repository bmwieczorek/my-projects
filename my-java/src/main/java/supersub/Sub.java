package supersub;

import java.lang.reflect.Field;

class Sup{
	
	public Sup() {
		Class<? extends Sup> class1 = this.getClass();
		System.out.println(class1);
		try {
			Field[] fields = class1.getFields();
			for (Field field : fields) {
				field.setAccessible(true);
				System.out.println(field);
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}		
	}
}

public class Sub extends Sup{
	String name = "";
	public Sub() {
		System.out.println("Subclass name:" + name);
	}
	public static void main(String[] args) {
		new Sub();		
	}
}
