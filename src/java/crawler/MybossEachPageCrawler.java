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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import utilities.ElementChecker;
import utilities.EscapseHTMLUtils;
import utilities.TestUtil;

/**
 *
 * @author HP
 */
public class MybossEachPageCrawler extends BaseCrawler implements Runnable {

    private String url;
    private String categoryName;

    public MybossEachPageCrawler(String url, String categoryName, ServletContext context) {
        super(context);
        this.url = url;
        this.categoryName = categoryName;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            String realUrl = DataContants.getInstance().getYouSportLink(context) + url;
            reader = getBufferedReaderForURL(realUrl);
            String document = getModelDocument(reader);
            stAXParserForModel(document);
        } catch (IOException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    private void stAXParserForModel(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = TestUtil.refineHtml(document);
        Model model = null;
        XMLEventReader eventReader = parseStringToXMLEventReader(document);
        String brand = "";
        String name = "";
        String price;
        String link = "";
        String detailLink = "";
        ProductDAO proDao = new ProductDAO();
        CategoryDAO cateDao = new CategoryDAO();
        boolean isStart = false;
        while (eventReader.hasNext()) {
            String tagName = "";
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                tagName = startElement.getName().getLocalPart();
                if ("div".equals(tagName)) {
                    isStart = true;
                } else if ("a".equals(tagName) && isStart) {
                    event = (XMLEvent) eventReader.next();
                    if (event.isCharacters()) {
                        continue;
                    }
                    startElement = event.asStartElement();
                    Attribute attSrc = startElement.getAttributeByName(new QName("src"));
                    link = attSrc == null ? "" : attSrc.getValue();

                    event = (XMLEvent) eventReader.next();
                    event = eventReader.nextTag();
                    event = (XMLEvent) eventReader.next();
                    event = eventReader.nextTag();
                    event = eventReader.nextTag();
                    event = eventReader.nextTag();
                    startElement = event.asStartElement();
                    Attribute attHref = startElement.getAttributeByName(new QName("href"));
                    detailLink = DataContants.getInstance().getYouSportLink(context) + attHref.getValue();
                    event = (XMLEvent) eventReader.next();
                    name = event.asCharacters().getData();

                    event = (XMLEvent) eventReader.next();
                    event = eventReader.nextTag();
                    event = eventReader.nextTag();
                    event = eventReader.nextTag();
                    event = eventReader.nextTag();
                    event = (XMLEvent) eventReader.next();
                    price = event.asCharacters().getData().trim();
                    isStart = false;
                    try {
                        price = price.replaceAll("\\D+", "");
                        double realPrice = Double.parseDouble(price);
                        TblCategory categoryID = cateDao.getFirstCategoryByName(categoryName);
                        TblProduct product = new TblProduct(name, realPrice, link, detailLink, categoryID);
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
            if (!isStart && line.contains("<div class=\"view-content\">")) {
                isStart = true;
            }
            if (isStart) {
                document += line.trim();
            }
            if (isStart && line.contains("<h2 class=\"element-invisible\">")) {
                break;
            }

        }

        return EscapseHTMLUtils.encodeHtml(document);
    }

    private String getBrand(XMLEventReader eventReader) {
        XMLEvent event = null;
        String brand = "";
        while (eventReader.hasNext()) {
            try {
                event = (XMLEvent) eventReader.next();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "h1", "class", "title")) {
                    eventReader.next();
                    XMLEvent value = (XMLEvent) eventReader.next();
                    return value.asCharacters().getData();
                }
            }
        }
        return brand;
    }

    private String getName(XMLEventReader eventReader, String brand) {

        String name = "";
        while (eventReader.hasNext()) {
            XMLEvent event = null;
            event = (XMLEvent) eventReader.next();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "div", "class", "name")) {
                    eventReader.next();
                    XMLEvent value = (XMLEvent) eventReader.next();
                    return value.asCharacters().getData();
                }
            }
        }
        return name;
    }

    private int getPrice(XMLEventReader eventReader) {
        XMLEvent event = null;
        int price = 0;
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "span", "class", "uc-price")) {
                    eventReader.next();
                    event = (XMLEvent) eventReader.next();

                    return price = Integer.parseInt(event.asCharacters().getData());
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

    private String getDetail1(XMLEventReader eventReader) {
        XMLEvent event = null;
        String data = "";
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "div", "class", "product-right")) {
                    eventReader.next();
                    XMLEvent value = (XMLEvent) eventReader.next();
                    if (value.isCharacters()) {
                        data = value.asCharacters().getData();
                        return data;
                    }
                }
            }
        }

        return data;
    }

}
