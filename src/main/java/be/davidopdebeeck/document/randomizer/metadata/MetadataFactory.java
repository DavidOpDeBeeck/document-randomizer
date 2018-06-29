package be.davidopdebeeck.document.randomizer.metadata;

import be.davidopdebeeck.document.randomizer.input.Input;

@FunctionalInterface
public interface MetadataFactory<M extends Metadata, I extends Input> {

    M createMetadata(I input);
}
