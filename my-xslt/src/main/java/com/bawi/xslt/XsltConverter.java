package com.bawi.xslt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class XsltConverter {
    /**
     * Additional XSLT parameter
     */
    public static final String LNIATA = "LNIATA";

    /**
     * Additional XSLT parameter
     */
    public static final String SCHEMA_VERSION = "SCHEMA_VERSION";

    private static final Logger LOGGER = LoggerFactory.getLogger(XsltConverter.class);

    private final Templates xsltTemplates;
    private final Map<String, String> staticTransformerParams = new HashMap<String, String>();

    public XsltConverter(final File xsltFile) throws TransformerConfigurationException {
        this(new StreamSource(xsltFile));
    }

    public XsltConverter(final InputStream xsltFileStream)
            throws TransformerConfigurationException {
        this(new StreamSource(xsltFileStream));
    }

    public XsltConverter(final Resource xsltFile)
            throws TransformerConfigurationException, IOException {
        this(xsltFile.getInputStream());
    }

    private XsltConverter(final Source xsltSource) throws TransformerConfigurationException {
        SAXTransformerFactory transFact = prepareTransformerFactory();
        xsltTemplates = transFact.newTemplates(xsltSource);
    }

    /**
     * Converts XML payload using XSLT transformation provided in constructor.
     * 
     * @param inputXmlData
     *            input XML as String
     * @return resulting XML as String
     */
    public String convert(String inputXmlData) {
        return convert(inputXmlData, Collections.<String, String> emptyMap());
    }

    /**
     * Converts XML payload using XSLT transformation provided in constructor.
     * 
     * @param inputXmlData
     *            input XML as String
     * @param parameters
     *            additional XSLT parameters
     * @return resulting XML as String
     */
    public String convert(String inputXmlData, Map<String, String> parameters) {
        // NOTE: XSLT is up to an order of magnitude slower on DOMSource than on StreamSource - so even
        // if you have a DOM Document, it's better to convert it to String and use StreamSource here rather
        // than directly using DOMSource.
        Source source = new StreamSource(new ByteArrayInputStream(inputXmlData.getBytes(Charset.forName("UTF-8"))));
        return convert(source, parameters);
    }

    /**
     * Converts XML payload to XML with flat structure.
     * 
     * @param xmlSource
     * @return
     */
    public String convert(final File xmlFile) {
        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        return convert(xmlSource);
    }

    /**
     * Converts XML payload to XML with flat structure.
     * 
     * @param xmlSource
     * @return
     */
    public String convert(final Source xmlSource) {
        return convert(xmlSource, staticTransformerParams);
    }

    /**
     * Converts XML payload to XML with flat structure.
     * 
     * @param xmlSource
     * @param parameters
     *            additional parameters to be passed to XSLT transformer
     * @return
     */
    public String convert(final Source xmlSource, final Map<String, String> parameters) {
        LOGGER.debug("starting conversion");
        StringWriter writer = new StringWriter();
        try {
            Transformer trans = prepareTransformer(parameters);
            trans.transform(xmlSource, new StreamResult(writer));
        } catch (TransformerException e) {
            LOGGER.error("Transformation exception ", e);
            return null;
        }
        return writer.toString();
    }

    /**
     * Allows to pass additional parameters (such us LNIATA, SCHEMA_VERSION) to XSL transformation.
     * 
     * @param param
     */
    public void setParameter(final String param, final String value) {
        LOGGER.debug("setting XSLT " + param + " to " + value);
        staticTransformerParams.put(param, value);
    }

    private Transformer prepareTransformer(final Map<String, String> parameters)
            throws TransformerConfigurationException {
        Transformer trans = xsltTemplates.newTransformer();

        for (Entry<String, String> entry : parameters.entrySet()) {
            trans.setParameter(entry.getKey(), entry.getValue());
        }
        return trans;
    }

    private SAXTransformerFactory prepareTransformerFactory() throws TransformerConfigurationException {
        TransformerFactory transFact = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", getClass()
                .getClassLoader());
        transFact.setErrorListener(new LoggingXsltErrorListener());
        if (!transFact.getFeature(SAXTransformerFactory.FEATURE)) {
            throw new TransformerConfigurationException(
                    "XSLT transformer does not have required SAXTransformerFactory feature");
        }
        return (SAXTransformerFactory) transFact;
    }
}

class LoggingXsltErrorListener implements ErrorListener {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingXsltErrorListener.class);

    @Override
    public void warning(final TransformerException exception) throws TransformerException {
        LOG.warn("XSLT warning: ", exception);
        throw exception;
    }

    @Override
    public void error(final TransformerException exception) throws TransformerException {
        LOG.error("XSLT error: ", exception);
        throw exception;
    }

    @Override
    public void fatalError(final TransformerException exception) throws TransformerException {
        LOG.error("XSLT fatal error: ", exception);
        throw exception;
    }
}