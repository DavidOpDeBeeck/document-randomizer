package be.davidopdebeeck.document.randomizer.document;

import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

public interface DocumentFactory<D extends Document> {

    Flowable<D> createDocument(InputSource<?> source);
}
