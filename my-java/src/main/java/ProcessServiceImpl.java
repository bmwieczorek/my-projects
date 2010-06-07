import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Report {
    Date date;

    int value;
}

interface Processor {
    void process(Report report);
}

class DateProcessor implements Processor {
    public void process(Report report) {
        report.date = new Date();
    }
}

class ValueProcessor implements Processor {
    public void process(Report report) {
        report.value = 123;
    }
}

public class ProcessServiceImpl {

    public static void main(String[] args) {
        Report report = new Report();
        List<Processor> processors = new ArrayList<Processor>(asList(new DateProcessor(),
                new ValueProcessor()));
        for (Processor processor : processors) {
            processor.process(report);
        }

    }

}
