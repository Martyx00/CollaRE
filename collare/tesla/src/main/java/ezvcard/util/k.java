package ezvcard.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/* compiled from: XmlUtils */
public final class k {
    public static Document a() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document a(String str) {
        try {
            return a(new StringReader(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document a(Reader reader) {
        return a(new InputSource(reader));
    }

    private static Document a(InputSource inputSource) {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setIgnoringComments(true);
        a(newInstance);
        try {
            return newInstance.newDocumentBuilder().parse(inputSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(DocumentBuilderFactory documentBuilderFactory) {
        HashMap hashMap = new HashMap();
        hashMap.put("http://apache.org/xml/features/disallow-doctype-decl", true);
        hashMap.put("http://xml.org/sax/features/external-general-entities", false);
        hashMap.put("http://xml.org/sax/features/external-parameter-entities", false);
        hashMap.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        for (Map.Entry entry : hashMap.entrySet()) {
            try {
                documentBuilderFactory.setFeature((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            } catch (ParserConfigurationException unused) {
            }
        }
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);
    }

    public static String a(Node node) {
        return a(node, new HashMap());
    }

    public static String a(Node node, Map<String, String> map) {
        try {
            StringWriter stringWriter = new StringWriter();
            a(node, stringWriter, map);
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(Node node, Writer writer, Map<String, String> map) {
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            a(newTransformer, map);
            newTransformer.transform(new DOMSource(node), new StreamResult(writer));
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerFactoryConfigurationError e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void a(Transformer transformer, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                transformer.setOutputProperty(entry.getKey(), entry.getValue());
            } catch (IllegalArgumentException unused) {
            }
        }
    }
}
