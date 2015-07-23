package converter;

import org.junit.Assert;
import org.junit.Test;

import converter.model.backend.ImFlight;
import converter.model.backend.ImLeg;
import converter.model.backend.ImSegment;
import converter.model.service.Flight;
import converter.model.service.Leg;
import converter.model.service.Segment;
import junit.framework.TestCase;

public class FlightConverterTest extends TestCase {

    FlightConverter flightConverter = new FlightConverter();

    private final static String name = "Ania";
    private final static int size = 38;

    @Test
    public void testConvertFlightToImFlight() {
        // given
        Flight flight = createFlight(name, size);

        // when
        ImFlight imFlight = flightConverter.convert(flight);

        // then
        assertImFlightHasImLegWithValues(imFlight, name, size);
        assertImFlightHasImSegmentWithValues(imFlight, name, size);
    }

    private void assertImFlightHasImLegWithValues(ImFlight imFlight, String name, int size) {
        ImLeg imLeg = imFlight.getImLeg();
        Assert.assertNotNull(imLeg);
        Assert.assertEquals(name, imLeg.getImName());
        Assert.assertEquals(size, imLeg.getImSize());
    }

    private void assertImFlightHasImSegmentWithValues(ImFlight imFlight, String name, int size) {
        ImSegment imSegment = imFlight.getImSegment();
        Assert.assertNotNull(imSegment);
        Assert.assertEquals(name, imSegment.getImName());
        Assert.assertEquals(size, imSegment.getImSize());
    }

    private Flight createFlight(String name, int size) {
        Leg leg = new Leg().withName(name).withSize(size);
        Segment segment = new Segment().withName(name).withSize(size);
        Flight flight = new Flight().withLeg(leg).withSegment(segment);
        return flight;
    }

}
