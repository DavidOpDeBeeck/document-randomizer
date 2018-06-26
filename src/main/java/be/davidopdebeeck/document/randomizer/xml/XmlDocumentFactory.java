package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

import static java.lang.String.format;

public class XmlDocumentFactory implements DocumentFactory<XmlDocument> {

    private final SAXReader saxReader;

    public XmlDocumentFactory() {
        this.saxReader = new SAXReader();
    }

    @Override
    public Flowable<XmlDocument> createDocument(InputSource<?> source) {
        return source.getSource().map(this::mapToXmlDocument);
    }

    private XmlDocument mapToXmlDocument(Input input) {
        try {
            return new XmlDocument(saxReader.read(new StringReader(input.getContent())));
        } catch (Exception e) {
            throw new RuntimeException(format("Failed to extract document from %s", input.getContent()));
        }
    }
}
