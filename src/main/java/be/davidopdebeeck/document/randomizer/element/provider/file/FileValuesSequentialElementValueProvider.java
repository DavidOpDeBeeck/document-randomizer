package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileValuesSequentialElementValueProvider implements ElementValueProvider {

    private final List<String> values;
    private final AtomicInteger currentCount;

    FileValuesSequentialElementValueProvider(List<String> values) {
        this.values = values;
        this.currentCount = new AtomicInteger();
    }

    @Override
    public String get() {
        return values.get(currentCount.getAndIncrement() % values.size());
    }
}
