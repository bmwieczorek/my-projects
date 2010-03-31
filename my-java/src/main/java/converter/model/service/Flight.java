package converter.model.service;


public class Flight {
	private Segment segment;
	private Leg leg;

	public Segment getSegment() {
		return segment;
	}

	public Leg getLeg() {
		return leg;
	}

	public Flight withLeg(Leg leg) {
		this.leg = leg;
		return this;
	}

	public Flight withSegment(Segment segment) {
		this.segment = segment;
		return this;
	}

}
