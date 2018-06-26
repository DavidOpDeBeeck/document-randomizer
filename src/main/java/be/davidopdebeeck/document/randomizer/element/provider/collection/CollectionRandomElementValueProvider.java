package be.davidopdebeeck.document.randomizer.element.provider.collection;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.*;

import static java.util.Collections.synchronizedList;

public class CollectionRandomElementValueProvider<O> implements ElementValueProvider {

    public static <O> ElementValueProvider randomValues(Collection<O> values) {
        return new CollectionRandomElementValueProvider<>(values);
    }

    private final List<O> values;
    private final Random random = new Random();

    private CollectionRandomElementValueProvider(Collection<O> values) {
        this.values = synchronizedList(new ArrayList<>(values));
    }

    @Override
    public String get() {
        int index = random.nextInt(values.size());
        return Objects.toString(values.get(index));
    }
}
