/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import contants.DataContants;
import dao.CategoryDAO;
import dao.ProductDAO;
import dto.Model;
import entity.TblCategory;
import entity.TblProduct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import utilities.ElementChecker;
import utilities.EscapseHTMLUtils;
import utilities.TestUtil;

/**
 *
 * @author HP
 */
public class NeoPageCrawler extends BaseCrawler implements Runnable {

    private String url;
    private String categoryName;

    public NeoPageCrawler(String url, String categoryName, ServletContext context) {
        super(context);
        this.url = url;
        this.categoryName = categoryName;
    }

    @Override
    public void run() {
        BufferedReader reader = null;

        try {
            reader = getBufferedReaderForURL(url);
            String document = getModelDocument(reader);
            stAXParserForModel(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stAXParserForModel(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = TestUtil.refineHtml(document);
        Model model = null;
        XMLEventReader eventReader = parseStringToXMLEventReader(document);

        String name = "";
        double price = 0;
        String link = "";
        String detailLink = "";
        boolean isStart = false;
        ProductDAO proDao = new ProductDAO();
        CategoryDAO cateDao = new CategoryDAO();
        while (eventReader.hasNext()) {
            String tagName = "";
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartDocument()) {
                event = (XMLEvent) eventReader.next();
            }
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                tagName = startElement.getName().getLocalPart();
                if ("div".equals(tagName)) {
                    isStart = true;
                } else if ("a".equals(tagName) && isStart) {

                    event = (XMLEvent) eventReader.next();//move to img tag
                    if (event.isCharacters()) {
                        continue;
                    }
                    startElement = event.asStartElement();
                    Attribute attSrc = startElement.getAttributeByName(new QName("src"));
                    if(attSrc == null){
                        link = "";
                    }else{
                        link = DataContants.getInstance().getNeoSportLink(context) + attSrc.getValue();
                    }

                    for (int i = 0; i <= 91; i++) {
                        event = eventReader.nextTag();
                        if (i == 16 || i == 20 || i == 25 || i == 27 || i == 29 || i == 31 || i == 33 || i == 35 || i == 37) {
                            eventReader.next();
                        }
                        if (i == 39 || i == 41 || i == 43 || i == 45 || i == 47 || i == 49 || i == 51 || i == 53 || i == 55) {
                            eventReader.next();
                        }
                        if (i == 57 || i == 59 || i == 61 || i == 63 || i == 65 || i == 67 || i == 69 || i == 71 || i == 80 || i == 84) {
                            eventReader.next();
                        }
                    }
                    event = eventReader.nextTag();
                    startElement = event.asStartElement();
                    Attribute attHref = startElement.getAttributeByName(new QName("href"));
                    detailLink = attHref.getValue();
                    event = (XMLEvent) eventReader.next();
                    name = event.asCharacters().getData();

                    for (int i = 0; i <= 4; i++) {
                        event = eventReader.nextTag();
                    }
                    event = (XMLEvent) eventReader.next();
                    String s = event.asCharacters().getData();
                    String s2 = s.replaceAll("\\s", "");
                    String s3 = s2.replaceAll("\\W", "");
                    price = Double.parseDouble(s3);
                    isStart = false;
                    try {
                        TblCategory categoryID = cateDao.getFirstCategoryByName(categoryName);
                        TblProduct product = new TblProduct(name, price, link, detailLink, categoryID);
                        proDao.getInstance().saveProductWhenCrawling(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

    private String getModelDocument(BufferedReader reader) throws IOException {
        String line = "";
        String document = "<model>";
        boolean isStart = false;

        while ((line = reader.readLine()) != null) {
            if (!isStart && line.contains("<div class=\"row product-grid list-item shoes\">")) {
                isStart = true;
            }
            if (isStart) {
                document += line.trim();
            }
            if (isStart && line.contains("<div class=\"row\">")) {
                break;
            }

        }

        return EscapseHTMLUtils.encodeHtml(document);
    }

    private String getName(XMLEventReader eventReader) {

        String name = "";
        while (eventReader.hasNext()) {
            XMLEvent event = null;
            event = (XMLEvent) eventReader.next();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (ElementChecker.isElementWith(startElement, "div", "class", "title")) {
                    event = (XMLEvent) eventReader.next();
                    if ("div".equals(tagName)) {
                        event = (XMLEvent) eventReader.next();
                        StartElement nextEle = event.asStartElement();
                        String tag = nextEle.getName().getLocalPart();
                        if ("a".equals(tag)) {
                            event = (XMLEvent) eventReader.next();
                            return name = event.asCharacters().getData();
                        }
                    }

                }
            }
        }
        return name;
    }

    private double getPrice(XMLEventReader eventReader) {
        XMLEvent event = null;
        double price = 0;
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (ElementChecker.isElementWith(startElement, "span", "class", "new pull-left")) {
                    event = (XMLEvent) eventReader.next();
                    String s = event.asCharacters().getData();
                    String s2 = s.replaceAll("\\s", "");
                    String s3 = s2.replaceAll("\\W", "");

                    return price = Double.parseDouble(s3);

                }
            }
        }
        return price;
    }

    private String getImageLink(XMLEventReader eventReader) {
        XMLEvent event = null;
        String link = "";
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "img")) {
                    link = getHref(startElement);
                    return link;
                }
            }
        }
        return link;
    }

    private String getHref(StartElement element) {
        Attribute href = element.getAttributeByName(new QName("src"));
        return href == null ? "" : href.getValue();
    }

    private String getDetailLink(XMLEventReader eventReader) {
        XMLEvent event = null;
        String detailLink = "";
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (ElementChecker.isElementWith(startElement, "div", "class", "product-thumb")) {
                    event = (XMLEvent) eventReader.next();
                    StartElement nextEle = event.asStartElement();
                    String tag = nextEle.getName().getLocalPart();
                    if ("a".equals(tag)) {

                        Attribute href = nextEle.getAttributeByName(new QName("href"));
                        detailLink = href.getValue();

                    }

                }
            }
        }
        return detailLink;
    }
}
