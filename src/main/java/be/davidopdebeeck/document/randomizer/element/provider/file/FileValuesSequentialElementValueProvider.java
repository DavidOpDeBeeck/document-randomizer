package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.collection.CollectionSequentialElementValueProvider;

import java.io.File;

import static be.davidopdebeeck.document.randomizer.element.provider.file.FileUtils.readFileLines;

public class FileValuesSequentialElementValueProvider {

    public static ElementValueProvider sequentialValues(File file) {
        return CollectionSequentialElementValueProvider.sequentialValues(readFileLines(file));
    }
}
