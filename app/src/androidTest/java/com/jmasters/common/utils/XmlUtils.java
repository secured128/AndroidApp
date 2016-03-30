package com.jmasters.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtils {
    /**
     * Convert a w3c dom node to a InputStream
     * 
     * @param node
     * @return
     * @throws TransformerException
     */
    public static InputStream nodeToInputStream(Node node) throws TransformerException {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	Result outputTarget = new StreamResult(outputStream);
	Transformer t = TransformerFactory.newInstance().newTransformer();
	t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	t.transform(new DOMSource(node), outputTarget);
	return new ByteArrayInputStream(outputStream.toByteArray());
    }

    /**
     * create xml dom Document from string
     * 
     * @param xml
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document createXMLFromString(String xml) throws ParserConfigurationException, SAXException, IOException {
	InputSource is = new InputSource(new StringReader(xml));
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	factory.setNamespaceAware(true);
	DocumentBuilder builder = null;
	builder = factory.newDocumentBuilder();
	Document doc = builder.parse(is);
	return doc;
    }
}
