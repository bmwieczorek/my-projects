package converter;

import converter.model.backend.ImFlight;
import converter.model.backend.ImLeg;
import converter.model.backend.ImSegment;
import converter.model.service.Flight;
import converter.model.service.Leg;
import converter.model.service.Segment;

public class FlightConverter {
	LegConverter legConverter = new LegConverter();
	SegmentConverter segmentConverter = new SegmentConverter();

	ImFlight convert(Flight flight) {
		Leg leg = flight.getLeg();
		Segment segment = flight.getSegment();
		ImLeg imLeg = legConverter.convert(leg);
		ImSegment imSegment = segmentConverter.convert(segment);
		return new ImFlight().withImLeg(imLeg).withImSegment(imSegment);
	}

	Flight convert(ImFlight imFlight) {
		ImLeg imLeg = imFlight.getImLeg();
		ImSegment imSegment = imFlight.getImSegment();
		Leg leg = legConverter.convert(imLeg);
		Segment segment = segmentConverter.convert(imSegment);
		return new Flight().withLeg(leg).withSegment(segment);
	}
}
