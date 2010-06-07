package converter;

import converter.model.backend.ImSegment;
import converter.model.service.Segment;

public class SegmentConverter {
    ImSegment convert(Segment segment) {
        String name = segment.getName();
        int size = segment.getSize();
        String imName = name;
        int imSize = size;
        return new ImSegment().withImName(imName).withImSize(imSize);
    }

    Segment convert(ImSegment imSegment) {
        String imName = imSegment.getImName();
        int imSize = imSegment.getImSize();
        String name = imName;
        int size = imSize;
        return new Segment().withName(name).withSize(size);
    }

}
