package com.geekcap.javaworld.sparkexample;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import scala.Tuple2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VCNProcessor {

    static void processVCN(String input, String output, String master) throws IOException, URISyntaxException {
        SparkConf conf;
        if (master == null) {
            conf = new SparkConf().setAppName("VCN Processor");
        } else {
            conf = new SparkConf().setMaster(master).setAppName("VCN Processor");
        }

        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

//        Path path = new Path("hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/data-ingestion-service/ASDS/PNR/year=2016/month=07/day=08/");
//        URI uri = new URI("hdfs://hadoop01.sgdcelab.sabre.com:8020");
//        FileSystem fileSystem = FileSystem.get(uri, sc.hadoopConfiguration());
//
//        List<String> pathList = getPathsWithHadoopListFiles(fileSystem, path);
//        System.out.println("Found: " + pathList.size());
//        System.out.println(pathList);
//        String[] paths = pathList.toArray(new String[pathList.size()]);

        //DataFrame df = sqlContext.read().format("com.databricks.spark.avro").load(paths);

        //DataFrame df = sqlContext.read().format("com.databricks.spark.avro").load("hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/data-ingestion-service/ASDS/PNR/year=2016/month=07/day=08/*");
        DataFrame df = sqlContext.read().format("com.databricks.spark.avro").load(input);
            JavaRDD<VPData> vpDataJavaRDD = df
                    .toJavaRDD()
                    .map(row -> new String((byte[]) row.getAs(14)))
                    .flatMap(VCNProcessor::createVPData)
                    .distinct();
            vpDataJavaRDD.saveAsTextFile(output);
    }

    private static List<String> listWholeFiles(JavaSparkContext sc, String path) {
        return sc.wholeTextFiles(path)
                .map((Function<Tuple2<String, String>, String>) Tuple2::_1)
                .collect();
    }

    private static List<String> getPathsWithHadoopListFiles(FileSystem fileSystem, Path path) {
        List<String> paths = new ArrayList<>();
        try {
            RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(path, true);
            while (locatedFileStatusRemoteIterator.hasNext()){
                LocatedFileStatus status = locatedFileStatusRemoteIterator.next();
                String pathAsString = status.getPath().toString();
                paths.add(pathAsString);
            }
            return paths;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
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
            XPathExpression xExprRemarks = xPath.compile("/SabreASDS/GetReservationRS/Reservation/Remarks/Remark[@type='HS']/RemarkLines/RemarkLine/Text[starts-with(.,'*/VCN') or starts-with(.,'*VCN')]/text()");

            final String pnrLocator = (String) xExprRecordLocator.evaluate(document, XPathConstants.STRING);
            final String pcc = (String) xExprPseudoCityCode.evaluate(document, XPathConstants.STRING);
            final NodeList remarks = (NodeList) xExprRemarks.evaluate(document, XPathConstants.NODESET);

            return IntStream.range(0, remarks.getLength())
                    .mapToObj(i -> remarks.item(i).getNodeValue())
                    .map(vcnRemarkLine -> {
                        VPData vpData = new VPData(pnrLocator, pcc, vcnRemarkLine);
                        System.out.println(vpData);
                        return vpData;
                    })
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        if( args.length == 0 )
        {
            System.out.println( "Usage: VCN Processor <input> <output> <master>" );
            System.exit( 0 );
        }

        // for windows we need to set hadoop.home.dir to parent dir of bin/winutils.exe
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("hadoop.home.dir", System.getProperty("user.dir"));
        }

        processVCN(args[0], args[1], args.length == 3 ? args[2] : null);

    }
}
