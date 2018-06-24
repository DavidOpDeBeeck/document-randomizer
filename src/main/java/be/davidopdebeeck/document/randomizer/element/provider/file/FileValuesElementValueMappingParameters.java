package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;

import java.io.File;

public class FileValuesElementValueMappingParameters implements ElementValueMappingParameters {

    private final File file;
    private final boolean random;

    public FileValuesElementValueMappingParameters(File file) {
        this.file = file;
        this.random = false;
    }

    public FileValuesElementValueMappingParameters(File file, boolean random) {
        this.file = file;
        this.random = random;
    }

    public File getFile() {
        return file;
    }

    public boolean isRandom() {
        return random;
    }
}
