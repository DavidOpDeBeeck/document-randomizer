package be.davidopdebeeck.document.randomizer.element.provider.collection;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.synchronizedList;

public class CollectionSequentialElementValueProvider<O> implements ElementValueProvider {

    public static <O> ElementValueProvider sequentialValues(Collection<O> values) {
        return new CollectionSequentialElementValueProvider<>(values);
    }

    private final List<O> values;
    private final AtomicInteger currentCount;

    private CollectionSequentialElementValueProvider(Collection<O> values) {
        this.values = synchronizedList(new ArrayList<>(values));
        this.currentCount = new AtomicInteger();
    }

    @Override
    public String get() {
        O value = values.get(currentCount.getAndIncrement() % values.size());
        return Objects.toString(value);
    }
}
