package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.element.Element;
import be.davidopdebeeck.document.randomizer.element.Element;
import org.dom4j.Node;

public class XmlElement implements Element {

    private final Node node;

    XmlElement(Node node) {
        this.node = node;
    }

    @Override
    public void setValue(String value) {
        node.setText(value);
    }

    @Override
    public String getValue() {
        return node.getText();
    }
}
