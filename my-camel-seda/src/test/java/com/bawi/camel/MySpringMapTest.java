package com.bawi.camel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpringMapTest {
    public enum ReportType { SUMMARY, DETAILED };
    public interface Report {};
    public static class SummaryReport implements Report {};
    public static class ReportFactory {
        private Map<ReportType, Report> map = new HashMap<ReportType, Report>();
        public Report getReport(ReportType reportType) {
            return map.get(reportType);
        }
        public void setMap(Map<ReportType, Report> map) {
            this.map = map;
        }
    }
    
    @Test
    public void test() { 
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"my-spring-map.xml"});
        ReportFactory reportFactory = context.getBean(ReportFactory.class);
        Report report = reportFactory.getReport(ReportType.SUMMARY);
        Assert.assertTrue(report instanceof SummaryReport);
        context.close();
    }
}
