package designpatterns.adapterpattern.classadapter;

public class Client {
	private Target target;

	void performRequest() {
		target.request();
	}

	public Client(Target target) {
		this.target = target;
	}

	public static void main(String[] args) {
		//client is completely decoupled from adaptee
		Target target = new ClassAdapter();
		Client client = new Client(target);
		client.performRequest();
	}
}
