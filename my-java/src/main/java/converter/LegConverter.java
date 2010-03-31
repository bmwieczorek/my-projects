package converter;

import converter.model.backend.ImLeg;
import converter.model.service.Leg;

public class LegConverter {
	ImLeg convert(Leg leg) {
		String name = leg.getName();
		int size = leg.getSize();
		String imName = name;
		int imSize = size;
		return new ImLeg().withImName(imName).withImName(imSize);
	}

	Leg convert(ImLeg imLeg) {
		String imName = imLeg.getImName();
		int imSize = imLeg.getImSize();
		String name = imName;
		int size = imSize;
		return new Leg().withName(name).withSize(size);
	}

}
