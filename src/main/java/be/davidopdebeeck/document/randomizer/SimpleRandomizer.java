package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.document.DocumentProcessingService;
import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import be.davidopdebeeck.document.randomizer.metadata.Metadata;
import be.davidopdebeeck.document.randomizer.metadata.MetadataFactory;
import be.davidopdebeeck.document.randomizer.result.Result;
import io.reactivex.Flowable;

public class SimpleRandomizer<D extends Document> implements Randomizer {

    private final DocumentFactory<D> documentFactory;
    private final DocumentProcessingService<D> documentProcessingService;

    SimpleRandomizer(DocumentFactory<D> documentFactory,
                     DocumentProcessingService<D> documentProcessingService) {
        this.documentFactory = documentFactory;
        this.documentProcessingService = documentProcessingService;
    }

    @Override
    public Flowable<String> randomize(InputSource<?> inputSource) {
        return inputSource.getSource()
                .map(documentFactory::createDocument)
                .map(this::processDocument)
                .map(Document::toString);
    }

    @Override
    public <M extends Metadata, I extends Input> Flowable<Result<M>> randomize(InputSource<I> inputSource, MetadataFactory<M, I> metadataFactory) {
        return inputSource.getSource()
                .map(input -> {
                    D document = documentFactory.createDocument(input);
                    M metadata = metadataFactory.createMetadata(input);
                    return new Result<>(metadata, processDocument(document).toString());
                });
    }

    private D processDocument(D document) {
        documentProcessingService.process(document);
        return document;
    }
}
