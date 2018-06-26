package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.io.File;
import java.util.List;
import java.util.Random;

import static be.davidopdebeeck.document.randomizer.element.provider.file.FileUtils.readFileLines;

public class FileValuesRandomElementValueProvider implements ElementValueProvider {

    public static ElementValueProvider randomValues(File file) {
        return new FileValuesRandomElementValueProvider(readFileLines(file));
    }

    private final List<String> values;

    private FileValuesRandomElementValueProvider(List<String> values) {
        this.values = values;
    }

    @Override
    public String get() {
        int index = new Random().nextInt(values.size());
        return values.get(index);
    }
}
