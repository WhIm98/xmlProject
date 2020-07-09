/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contants;

import java.io.File;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author HP
 */
public class DataContants {
    private static final String XMLFILE = "/WEB-INF/contains.xml";
    
    private static DataContants instance;
    private static final Object LOCK = new Object();

    public DataContants() {
    }

    public static DataContants getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new DataContants();
            }
        }
        return instance;
    }

    public String getYouSportLink(ServletContext context) {
        try {
            String realPath = context.getRealPath("/");
            String filePath = realPath + XMLFILE;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(filePath);
            Document doc = db.parse(f);
            if (doc != null) {
                XPathFactory xpf = XPathFactory.newInstance();
                XPath xpath = xpf.newXPath();
                Node node = (Node) xpath.evaluate("//yousport", doc, XPathConstants.NODE);
                if (node != null) {
                    return node.getTextContent();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public String getNeoSportLink(ServletContext context) {
        try {
            String realPath = context.getRealPath("/");
            String filePath = realPath + XMLFILE;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(filePath);
            Document doc = db.parse(f);
            if (doc != null) {
                XPathFactory xpf = XPathFactory.newInstance();
                XPath xpath = xpf.newXPath();
                Node node = (Node) xpath.evaluate("//neosport", doc, XPathConstants.NODE);
                if (node != null) {
                    return node.getTextContent();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    
}
