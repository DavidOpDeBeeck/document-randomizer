package be.davidopdebeeck.document.randomizer.element;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ElementGroup implements Element {

    private final List<Element> elements;

    public ElementGroup(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public void setValue(String value) {
        elements.forEach(element -> element.setValue(value));
    }

    @Override
    public String getValue() {
        return elements.stream()
                .map(Element::getValue)
                .collect(joining(","));
    }
}
