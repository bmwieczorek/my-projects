package oop;

public class Inheritance {
	
	public static void main(String[] args) {
		
		Superclass superclass = new Superclass();
		superclass.superA = 0;
		//superclass.subA - does not exist;
		
		Subclass subclass = new Subclass();
		subclass.superA = 0;
		subclass.subA = 0;
		
		Superclass super_sub_class = new Subclass();
		super_sub_class.superA = 0;
		
		//Subclass sub_super_class = new Superclass(); - cannot convert
		
		/*
		 * allowed by compilator, but causes class cast exception
		 * 
		Subclass sub_super_class = (Subclass)new Superclass();
		sub_super_class.subA = 0;
		sub_super_class.superA = 0;
		*/
		
		Superclass s = new Superclass();
		Superclass ss = new Subclass();
		Subclass sss = new Subclass();
		System.out.println(s.superA + ":" + ss.superA + ":" + sss.superA); //result 100
		//if superA = 200 in Subclass then result 100:100:200 (derived property would overwrite base)
	}

}

//Figure
class Superclass {  
	int superA = 100;

}

//Rectangle - specific figure
//has all features for figure and may also have its own specific features
class Subclass extends Superclass {
	int subA;
	
}