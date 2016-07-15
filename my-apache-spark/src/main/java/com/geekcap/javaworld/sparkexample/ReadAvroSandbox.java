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

/**
 * Sample Spark application that counts the words in a text file
 */
public class ReadAvroSandbox {

//    private XPath xPath;
//    private XPathExpression xExprRecordLocator;
//    private XPathExpression xExprPseudoCityCode;
//    private XPathExpression xExprRemarks;
//
//    public ReadAvroSandbox() throws XPathExpressionException {
//        xPath = XPathFactory.newInstance().newXPath();
//        xExprRecordLocator = xPath.compile("/GetReservationRS/Reservation/BookingDetails/RecordLocator/text()");
//        xExprPseudoCityCode = xPath.compile("/GetReservationRS/Reservation/POS/Source/@PseudoCityCode");
//        xExprRemarks = xPath.compile("/GetReservationRS/Reservation/Remarks/Remark[@type='HS']/RemarkLines/RemarkLine/Text[starts-with(.,'*/VCN')]/text()");
//    }


    public static void wordCountJava7() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("Work Count App");

        JavaSparkContext sc = new JavaSparkContext(conf);


        SQLContext sqlContext = new SQLContext(sc);
        DataFrame df = sqlContext.read().format("com.databricks.spark.avro").load("src/test/resources/avro/data.1467982803298.avro");
        JavaRDD<VPData> vpDataJavaRDD = df
                // .filter("pnrLocator = 'DPIZGU'")
//                .show();
                .toJavaRDD()
                .map((Function<Row, String>) v1 -> new String((byte[]) v1.getAs(14)))
                // .map((Function<String, List<VPData>>) xml -> createVPData(xml)).take(100);
                .flatMap((FlatMapFunction<String, VPData>) xml -> createVPData(xml))
                .distinct();


        vpDataJavaRDD.saveAsTextFile("abc.csv");
//        DataFrame dataFrame = sqlContext.createDataFrame(vpDataJavaRDD, VPData.class);
//        dataFrame.write();

//                .distinct()
//                .saveAsTextFile("aaaa.csv");


//        df.filter("pnrLocator = 'DPIZGU'")
//                .limit(2)
//                .select("body")
//                .map(row -> row.)
//                .show();
//                .write()
//                .format("com.databricks.spark.avro")
//                .save("src/test/resources/avro/output");
//        df.printSchema();
//        df.show();
    }

    private static Set<VPData> createVPData(String xml) {
        try {
//            if (xml.contains("VCN")) {
//                System.out.println(xml);
//            }
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
            int length = remarks.getLength();
            for (int i = 0; i < length ; i++) {
                String vcnRemarkLine = remarks.item(i).getNodeValue();
                VPData vpData = new VPData(pnrLocator, pcc, vcnRemarkLine);
                System.out.println(vpData);
                vpDataSet.add(vpData);
            }

            return vpDataSet;
//            return IntStream.range(0, length)
//                    .mapToObj(i -> remarks.item(i).getNodeValue())
//                    .map(vcnRemarkLine -> {
//                        VPData vpData = new VPData(pnrLocator, pcc, vcnRemarkLine);
//                        System.out.println(vpData);
//                        return vpData;
//                    })
//                    .collect(Collectors.toList());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // for windows we need to set hadoop.home.dir to parent dir of bin/winutils.exe
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("hadoop.home.dir", System.getProperty("user.dir"));
        }
        wordCountJava7();

    }
}
