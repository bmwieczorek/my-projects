package generics;

import java.util.ArrayList;
import java.util.List;

class X {
}

class Y extends X {
}

class Z extends Y {
}

public class Utils<T> {
	void subList(List<? extends T> list) {
	}

	void superList(List<? super T> list) {
	}

	// void <Zz super T> superE(Zz list) {
	// }

	void subE(T t) {
	}

	<TT extends T> void subE2(TT tt) {
	}

	public static void main(String[] args) {
		Utils<Y> utils = new Utils<Y>();
		// utils.subE(new Object()); //will not compile
		// utils.subE(new X()); //will not compile
		utils.subE(new Y());
		utils.subE(new Z());

		// utils.subE2(new Object()); // will not compile
		// utils.subE2(new X()); // will not compile
		utils.subE2(new Y());
		utils.subE2(new Z());

		utils.superList(new ArrayList<Object>());
		utils.superList(new ArrayList<X>());
		utils.superList(new ArrayList<Y>());
		// utils.superList(new ArrayList<Z>()); //will not compile

		// utils.subList(new ArrayList<X>()); //will not compile
		// utils.subList(new ArrayList<Object>()); //will not compile
		utils.subList(new ArrayList<Y>());
		utils.subList(new ArrayList<Z>());

	}

}
