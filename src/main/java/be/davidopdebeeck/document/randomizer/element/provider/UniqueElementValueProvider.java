package be.davidopdebeeck.document.randomizer.element.provider;

import java.util.ArrayList;
import java.util.List;

public class UniqueElementValueProvider implements ElementValueProvider {

    static UniqueElementValueProvider onlyUniqueValues(ElementValueProvider delegate) {
        return new UniqueElementValueProvider(delegate);
    }

    private final ElementValueProvider delegate;
    private final List<String> usedValues = new ArrayList<>();

    public UniqueElementValueProvider(ElementValueProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public String get() {
        String value = delegate.get();

        if (usedValues.contains(value)) {
            return get();
        }

        usedValues.add(value);

        return value;
    }
}
