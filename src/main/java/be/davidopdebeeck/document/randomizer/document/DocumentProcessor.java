package be.davidopdebeeck.document.randomizer.document;

import be.davidopdebeeck.document.randomizer.element.finder.ElementFinder;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

public class DocumentProcessor<D extends Document> {

    private final ElementFinder elementFinder;
    private final ElementValueProvider elementValueProvider;

    DocumentProcessor(ElementFinder elementFinder,
                      ElementValueProvider elementValueProvider) {
        this.elementFinder = elementFinder;
        this.elementValueProvider = elementValueProvider;
    }

    D process(D document) {
        elementFinder.findElements(document)
                .forEach(element -> element.setValue(elementValueProvider.get()));
        return document;
    }
}
