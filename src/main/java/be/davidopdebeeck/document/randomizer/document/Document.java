package be.davidopdebeeck.document.randomizer.document;

import be.davidopdebeeck.document.randomizer.element.Element;

import java.util.List;

public interface Document {

    List<Element> findElementsBy(String xpath);

    String toString();
}
