package converter.model.backend;

public class ImLeg {
	private int imSize;
	private String imName;

	public int getImSize() {
		return imSize;
	}

	public String getImName() {
		return imName;
	}

	public ImLeg withImName(String imName) {
		this.imName = imName;
		return this;
	}

	public ImLeg withImName(int imSize) {
		this.imSize = imSize;
		return this;
	}

}
