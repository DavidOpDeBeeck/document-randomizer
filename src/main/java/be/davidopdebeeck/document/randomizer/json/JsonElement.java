package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.element.Element;
import com.jayway.jsonpath.DocumentContext;

public class JsonElement implements Element {

    private final DocumentContext document;
    private final String nodePath;

    JsonElement(DocumentContext document, String nodePath) {
        this.document = document;
        this.nodePath = nodePath;
    }

    @Override
    public void setValue(String value) {
        document.set(nodePath, value);
    }

    @Override
    public String getValue() {
        return document.read(nodePath);
    }
}
