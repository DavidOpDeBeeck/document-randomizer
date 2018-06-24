package be.davidopdebeeck.document.randomizer.element.finder;

import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMapping;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMapping;

public class ElementFinderFactory {

    public ElementFinder createElementFinder(ElementValueMapping elementValueMapping) {
        String xpath = elementValueMapping.getXpath();

        return elementValueMapping.groupNodesWithSameText()
                ? new ElementsWithSameTextFinder(xpath)
                : new SingleElementFinder(xpath);
    }
}
