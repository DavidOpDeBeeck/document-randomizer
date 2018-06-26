package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.collection.CollectionRandomElementValueProvider;

import java.io.File;

import static be.davidopdebeeck.document.randomizer.element.provider.file.FileUtils.readFileLines;

public class FileValuesRandomElementValueProvider {

    public static ElementValueProvider randomValues(File file) {
        return CollectionRandomElementValueProvider.randomValues(readFileLines(file));
    }
}
