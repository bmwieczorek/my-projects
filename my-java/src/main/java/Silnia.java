import java.util.ArrayList;
import java.util.List;


public class Silnia {

	/**
	 * @param args
	 */
	
	List<String> names = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Silnia s = new Silnia();
		s.names.add("Ania");
		s.names.add("Kasia");
		//s.names.add("Basia");
		
		System.out.println(s.testNames(s.names.size()-1));
		
		System.out.println(s.test(0));
		System.out.println(s.test(1));
		System.out.println(s.test(2));
		System.out.println(s.test(3));
	}
	
	public int sil(int n){
		if ( n == 0 ) return 1;
		
		return n * sil(n-1); 
	}

	public String test(int n){
		if ( n == 0 ) return "Restrictions.eq(0)";
		
		return "Restrictions.or(Restrictions.eq(" + n + ")," + test(n-1)  +  ")";
	}
	
	public String testNames(int n){
		if ( n == 0 ) return "Restrictions.eq(" + names.get(0) + ")";
		
		return "Restrictions.or(Restrictions.eq(" + names.get(n) + ")," + testNames(n-1)  +  ")";
	}

}


