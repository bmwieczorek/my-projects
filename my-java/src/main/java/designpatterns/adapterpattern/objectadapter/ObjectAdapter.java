package designpatterns.adapterpattern.objectadapter;

import designpatterns.adapterpattern.Adaptee;

public class ObjectAdapter extends TargetClass {

	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.makeRequest();
	}

}
