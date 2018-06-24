package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.List;
import java.util.Random;

public class FileValuesRandomElementValueProvider implements ElementValueProvider {

    private final List<String> values;

    FileValuesRandomElementValueProvider(List<String> values) {
        this.values = values;
    }

    @Override
    public String get() {
        int index = new Random().nextInt(values.size());
        return values.get(index);
    }
}
