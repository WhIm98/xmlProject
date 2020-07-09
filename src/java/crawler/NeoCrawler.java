/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dao.CategoryDAO;
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
import utilities.TestUtil;

/**
 *
 * @author HP
 */
public class NeoCrawler extends BaseCrawler implements Runnable {

    private String url;
    private String categoryName;

    public NeoCrawler(ServletContext context, String url, String categoryName) {

        super(context);
        this.url = url;
        this.categoryName = categoryName;
    }

    @Override
    public void run() {
        CategoryDAO category = new CategoryDAO();
        category.getInstance().saveCategoryWhileCrawling(categoryName);
        BufferedReader reader = null;
        try {
            reader = getBufferedReaderForURL(url);
            String document = getModelListDocument(reader);
            document = TestUtil.refineHtml(document);
            try {
                synchronized (BaseThread.getInstance()) {
                    while (BaseThread.isSuspended()) {
                        BaseThread.getInstance().wait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            List<String> modelLink = getModelLinks(document);
            for (int i = 0; i <= modelLink.size()-1; i++) {
                Thread pageCrawling = new Thread(new NeoPageCrawler(modelLink.get(i), categoryName, getContext()));
                pageCrawling.start();
                try {
                    synchronized (BaseThread.getInstance()) {
                        while (BaseThread.isSuspended()) {
                            BaseThread.getInstance().wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private String getModelListDocument(BufferedReader reader) throws IOException {
        String line = "";
        String document = "<doc>";
        boolean isStart = false;
        while ((line = reader.readLine()) != null) {
            if (!isStart && line.contains("<ul class=\"pagination\" role=\"navigation\">")) {
                isStart = true;
            }
            if (isStart) {
                document += line.trim();
            }
            if (isStart && line.contains("</ul>")) {
                break;
            }
        }
        return document;
    }

    private List<String> getModelLinks(String document)
            throws UnsupportedEncodingException, XMLStreamException {
        XMLEventReader eventReader = parseStringToXMLEventReader(document);
        XMLEvent event = null;
        List<String> links = new ArrayList<>();
        while (eventReader.hasNext()) {
            event = (XMLEvent) eventReader.next();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "a")) {
                    String link = getHref(startElement);
                    if (!link.contains("gio-hang")) {
                        links.add(link);
                    }
                }
            }
        }
        return links;
    }

    private String getHref(StartElement element) {
        Attribute href = element.getAttributeByName(new QName("href"));
        return href == null ? "" : href.getValue();
    }
}
