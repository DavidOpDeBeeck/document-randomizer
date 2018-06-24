package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.document.DocumentProcessingService;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.document.DocumentProcessingService;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

public class SimpleRandomizer<D extends Document> implements Randomizer<D> {

    private final DocumentFactory<D> documentFactory;
    private final DocumentProcessingService<D> documentProcessingService;

    SimpleRandomizer(DocumentFactory<D> documentFactory,
                     DocumentProcessingService<D> documentProcessingService) {
        this.documentFactory = documentFactory;
        this.documentProcessingService = documentProcessingService;
    }

    @Override
    public Flowable<D> randomize(InputSource<?> inputSource) {
        return documentFactory.createDocument(inputSource)
                .map(this::processDocument);
    }

    private D processDocument(D document) {
        documentProcessingService.process(document);
        return document;
    }
}
