package converter.model.backend;

public class ImFlight {
	private ImSegment imSegment;
	private ImLeg imLeg;

	public ImSegment getImSegment() {
		return imSegment;
	}

	public ImLeg getImLeg() {
		return imLeg;
	}

	public ImFlight withImLeg(ImLeg imLeg) {
		this.imLeg = imLeg;
		return this;
	}

	public ImFlight withImSegment(ImSegment imSegment) {
		this.imSegment = imSegment;
		return this;
	}

}
