package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.input.Input;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

import static java.lang.String.format;

public class XmlDocumentFactory implements DocumentFactory<XmlDocument> {

    private final SAXReader saxReader;

    public XmlDocumentFactory() {
        this.saxReader = new SAXReader();
    }

    @Override
    public <I extends Input> XmlDocument createDocument(I input) {
        return mapToXmlDocument(input);
    }

    private XmlDocument mapToXmlDocument(Input input) {
        try {
            return new XmlDocument(saxReader.read(new StringReader(input.getContent())));
        } catch (Exception e) {
            throw new RuntimeException(format("Failed to extract document from %s", input.getContent()));
        }
    }
}
