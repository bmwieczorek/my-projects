package converter.model.service;

public class Segment {
	private int size;
	private String name;
	public int getSize() {
		return size;
	}
	public String getName() {
		return name;
	}

	public Segment withName(String name) {
		this.name = name;
		return this;
	}

	public Segment withSize(int size) {
		this.size = size;
		return this;
	}

}
