package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.document.DocumentProcessingService;
import be.davidopdebeeck.document.randomizer.document.DocumentProcessorFactory;
import be.davidopdebeeck.document.randomizer.element.mapping.ElementValueMapping;
import be.davidopdebeeck.document.randomizer.json.JsonDocument;
import be.davidopdebeeck.document.randomizer.json.JsonDocumentFactory;
import be.davidopdebeeck.document.randomizer.xml.XmlDocument;
import be.davidopdebeeck.document.randomizer.xml.XmlDocumentFactory;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Randomizers {

    public static Randomizer<XmlDocument> xmlRandomizer(List<ElementValueMapping> elementValueMappings) {
        requireNonNull(elementValueMappings);
        return new SimpleRandomizer<>(
                new XmlDocumentFactory(),
                createDocumentProcessingService(elementValueMappings));
    }

    public static Randomizer<JsonDocument> jsonRandomizer(List<ElementValueMapping> elementValueMappings) {
        requireNonNull(elementValueMappings);
        return new SimpleRandomizer<>(
                new JsonDocumentFactory(),
                createDocumentProcessingService(elementValueMappings));
    }

    private static <D extends Document> DocumentProcessingService<D> createDocumentProcessingService(List<ElementValueMapping> elementValueMappings) {
        DocumentProcessorFactory<D> processorFactory = new DocumentProcessorFactory<>();
        return new DocumentProcessingService<>(elementValueMappings.stream()
                .map(processorFactory::create)
                .collect(toList()));
    }
}
