package be.davidopdebeeck.document.randomizer.element.provider;

import java.util.ArrayList;
import java.util.List;

public class UniqueElementValueProvider implements ElementValueProvider {

    private static final int MAX_LOOKUP_COUNT = 100;

    public static UniqueElementValueProvider onlyUniqueValues(ElementValueProvider delegate) {
        return new UniqueElementValueProvider(delegate);
    }

    private final ElementValueProvider delegate;
    private final List<String> usedValues = new ArrayList<>();

    private UniqueElementValueProvider(ElementValueProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public String get() {
        return findUniqueValue(0);
    }

    private String findUniqueValue(int lookupCount) {
        String value = delegate.get();

        if (lookupCount > MAX_LOOKUP_COUNT) {
            throw new ElementValueProviderFailedException("Failed to provision unique value");
        }

        if (usedValues.contains(value)) {
            return findUniqueValue(++lookupCount);
        }

        usedValues.add(value);

        return value;
    }
}
