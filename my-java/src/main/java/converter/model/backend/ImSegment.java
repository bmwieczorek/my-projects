package converter.model.backend;

public class ImSegment {
	private int imSize;
	private String imName;

	public int getImSize() {
		return imSize;
	}

	public String getImName() {
		return imName;
	}

	public ImSegment withImName(String imName) {
		this.imName = imName;
		return this;
	}

	public ImSegment withImSize(int imSize) {
		this.imSize = imSize;
		return this;
	}

}
