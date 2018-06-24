package be.davidopdebeeck.document.randomizer.document;

import java.util.List;

public class DocumentProcessingService<D extends Document> {

    private final List<DocumentProcessor<D>> processors;

    public DocumentProcessingService(List<DocumentProcessor<D>> processors) {
        this.processors = processors;
    }

    public D process(D document) {
        D processed = document;
        for (DocumentProcessor<D> processor : processors) {
            processed = processor.process(processed);
        }
        return processed;
    }
}
