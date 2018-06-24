package be.davidopdebeeck.document.randomizer.element.finder;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;
import be.davidopdebeeck.document.randomizer.element.SingleElement;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SingleElementFinder implements ElementFinder {

    private final String xpath;

    SingleElementFinder(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public List<Element> findElements(Document xmlFile) {
        return xmlFile.findElementsBy(xpath).stream()
                .map(SingleElement::new)
                .collect(toList());
    }
}
