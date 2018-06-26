package be.davidopdebeeck.document.randomizer.element.finder;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;

import java.util.List;

@FunctionalInterface
public interface ElementFinder {

    List<Element> findElements(Document document);
}
