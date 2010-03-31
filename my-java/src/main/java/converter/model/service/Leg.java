package converter.model.service;

public class Leg {
	private int size;
	private String name;

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public Leg withName(String name) {
		this.name = name;
		return this;
	}

	public Leg withSize(int size) {
		this.size = size;
		return this;
	}

}
