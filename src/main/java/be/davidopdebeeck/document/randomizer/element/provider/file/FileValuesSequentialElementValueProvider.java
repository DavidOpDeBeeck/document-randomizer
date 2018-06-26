package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static be.davidopdebeeck.document.randomizer.element.provider.file.FileUtils.readFileLines;

public class FileValuesSequentialElementValueProvider implements ElementValueProvider {

    public static ElementValueProvider sequentialValues(File file) {
        return new FileValuesSequentialElementValueProvider(readFileLines(file));
    }

    private final List<String> values;
    private final AtomicInteger currentCount;

    private FileValuesSequentialElementValueProvider(List<String> values) {
        this.values = values;
        this.currentCount = new AtomicInteger();
    }

    @Override
    public String get() {
        return values.get(currentCount.getAndIncrement() % values.size());
    }
}
