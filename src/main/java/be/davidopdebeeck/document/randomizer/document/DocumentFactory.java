package be.davidopdebeeck.document.randomizer.document;

import be.davidopdebeeck.document.randomizer.input.Input;

public interface DocumentFactory<D extends Document> {

    <I extends Input> D createDocument(I input);
}
