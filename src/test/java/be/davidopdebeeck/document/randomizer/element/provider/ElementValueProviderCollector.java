package be.davidopdebeeck.document.randomizer.element.provider;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ElementValueProviderCollector {

    public static List<String> collect(ElementValueProvider provider, int times) {
        return IntStream.range(0, times)
                .mapToObj(i -> provider.get())
                .collect(toList());
    }
}
