package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.toList;
import static org.dom4j.io.OutputFormat.createPrettyPrint;

public class XmlDocument implements Document {

    private static final OutputFormat outputFormat = createPrettyPrint();

    private final org.dom4j.Document document;

    XmlDocument(org.dom4j.Document document) {
        this.document = document;
    }

    @Override
    public List<Element> findElementsBy(String xpath) {
        return findNodes(xpath).stream()
                .map(XmlElement::new)
                .collect(toList());
    }

    @Override
    public String toString() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            XMLWriter writer = new XMLWriter(outputStream, outputFormat);
            writer.write(document);
            writer.flush();
            writer.close();
            return new String(outputStream.toByteArray(), defaultCharset());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert xml file to string");
        }
    }

    @SuppressWarnings("unchecked")
    private List<Node> findNodes(String xpath) {
        return (List<Node>) document.selectNodes(xpath);
    }

}
