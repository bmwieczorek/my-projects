class AClass {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String name;
	private int age;

	public AClass() {
		name = "myDefaulfName";
		age = -1;
	}

	public AClass(int age) {
		this(); 
		this.age = age;
	}
	
	public AClass(String name, int age){
		this(age);
		this.name=name;
	}

}

public class ThisConstructor {

	public static void main(String[] args) {
		AClass class1 = new AClass();
		System.out.println(class1.getName() + class1.getAge());
		AClass class2 = new AClass(26);
		System.out.println(class2.getName() + class2.getAge());
		AClass class3 = new AClass("bawi",26);
		System.out.println(class3.getName() + class3.getAge());
	}

}
