package com.geekcap.javaworld.sparkexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

public class VCNProcessorJava7 {

    static void processVCN(String input, String output) {
        //SparkConf conf = new SparkConf().setMaster("local").setAppName("VCN Processor Java 7");
        SparkConf conf = new SparkConf().setAppName("VCN Processor");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        DataFrame df = sqlContext.read().format("com.databricks.spark.avro").load(input);
        JavaRDD<VPData> vpDataJavaRDD = df
                .toJavaRDD()
                .map(new Function<Row, String>() {
                    @Override
                    public String call(Row row) throws Exception {
                        return new String((byte[]) row.getAs(14));
                    }
                })
                .flatMap(new FlatMapFunction<String, VPData>() {
                    @Override
                    public Iterable<VPData> call(String xml) throws Exception {
                        return createVPData(xml);
                    }
                })
                .distinct();
        vpDataJavaRDD.saveAsTextFile(output);
    }

    private static Set<VPData> createVPData(String xml) {
        try {
            InputSource source = new InputSource(new StringReader(xml));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(source);

            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression xExprRecordLocator = xPath.compile("/SabreASDS/GetReservationRS/Reservation/BookingDetails/RecordLocator/text()");
            XPathExpression xExprPseudoCityCode = xPath.compile("/SabreASDS/GetReservationRS/Reservation/POS/Source/@PseudoCityCode");
            //XPathExpression xExprRemarks = xPath.compile("/GetReservationRS/Reservation/Remarks/Remark[@type='HS']/RemarkLines/RemarkLine/Text[starts-with(.,'*/VCN')]/text()");
            XPathExpression xExprRemarks = xPath.compile("/SabreASDS/GetReservationRS/Reservation/Remarks/Remark[@type='HS']/RemarkLines/RemarkLine/Text[starts-with(.,'*/VCN') or starts-with(.,'*VCN')]/text()");

            final String pnrLocator = (String) xExprRecordLocator.evaluate(document, XPathConstants.STRING);
            final String pcc = (String) xExprPseudoCityCode.evaluate(document, XPathConstants.STRING);
            final NodeList remarks = (NodeList) xExprRemarks.evaluate(document, XPathConstants.NODESET);

            Set<VPData> vpDataSet = new HashSet<>();
            for (int i = 0; i < remarks.getLength(); i++) {
                String vcnRemarkLine = remarks.item(i).getNodeValue();
                VPData vpData = new VPData(pnrLocator, pcc, vcnRemarkLine);
                System.out.println(vpData);
                vpDataSet.add(vpData);
            }
            return vpDataSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if( args.length == 0 )
        {
            System.out.println( "Usage: VCN Processor <input> <output>" );
            System.exit( 0 );
        }

        // for windows we need to set hadoop.home.dir to parent dir of bin/winutils.exe
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("hadoop.home.dir", System.getProperty("user.dir"));
        }

        processVCN(args[0], args[1]);

    }
}
