/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import utilities.TestUtil;

/**
 *
 * @author HP
 */
public class NeoCategoriesCrawler extends BaseCrawler {

    public NeoCategoriesCrawler(ServletContext context) {
        super(context);
    }

    public Map<String, String> getCategories(String url) {
        try (BufferedReader reader = getBufferedReaderForURL(url)) {
            String document = getCategoryDocument(reader);
            return stAXParserForCategories(document);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(NeoCategoriesCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getCategoryDocument(final BufferedReader reader) throws IOException {
        String line = "";
        String document = "<doc>";
        boolean isStart = false;
        while ((line = reader.readLine()) != null) {
            if (isStart && line.contains("</ul>")) {
                break;
            }
            if (isStart) {
                document += line.trim();
            }
            if (!isStart && line.contains("<ul>")) {
                isStart = true;
            }
        }
        return document;
    }

    public Map<String, String> stAXParserForCategories(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        document = TestUtil.refineHtml(document);
        XMLEventReader eventReader = parseStringToXMLEventReader(document);
        Map<String, String> categories = new HashMap<>();
        String link = "";
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if ("a".equals(tagName)) {
                    Attribute href = startElement.getAttributeByName(new QName("href"));
                    link = href.getValue();
                }
                if ("img".equals(tagName)) {
                    if (event.isStartElement()) {
                        event = (XMLEvent) eventReader.next();
                        if (event.isEndElement()) {
                            event = (XMLEvent) eventReader.next();
                            Characters categoryNameChars = event.asCharacters();
                            categories.put(link, categoryNameChars.getData());
                        }
                    }
                }

            }
        }
        return categories;
    }
}
