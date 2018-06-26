package be.davidopdebeeck.document.randomizer.document;

import be.davidopdebeeck.document.randomizer.element.finder.ElementFinder;
import be.davidopdebeeck.document.randomizer.element.finder.ElementFinderFactory;
import be.davidopdebeeck.document.randomizer.element.mapping.ElementValueMapping;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

public class DocumentProcessorFactory<D extends Document> {

    private final ElementFinderFactory elementFinderFactory;

    public DocumentProcessorFactory() {
        this.elementFinderFactory = new ElementFinderFactory();
    }

    public DocumentProcessor<D> create(ElementValueMapping mapping) {
        ElementFinder elementFinder = elementFinderFactory.createElementFinder(mapping);
        ElementValueProvider elementValueProvider = mapping.getElementValueProvider();
        return new DocumentProcessor<>(elementFinder, elementValueProvider);
    }
}
