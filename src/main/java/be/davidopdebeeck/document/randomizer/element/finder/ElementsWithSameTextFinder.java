package be.davidopdebeeck.document.randomizer.element.finder;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;
import be.davidopdebeeck.document.randomizer.element.ElementGroup;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ElementsWithSameTextFinder implements ElementFinder {

    private final String xpath;

    ElementsWithSameTextFinder(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public List<Element> findElements(Document document) {
        List<Element> elements = document.findElementsBy(xpath);

        return elements.stream()
                .collect(groupingBy(Element::getValue))
                .values()
                .stream()
                .map(ElementGroup::new)
                .collect(toList());
    }
}
