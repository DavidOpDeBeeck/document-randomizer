package be.davidopdebeeck.document.randomizer.element.provider.random;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.random.RandomNumberBetweenElementValueProvider.exclusiveBetween;
import static be.davidopdebeeck.document.randomizer.element.provider.random.RandomNumberBetweenElementValueProvider.inclusiveBetween;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberBetweenElementValueProviderTest {

    @Test
    public void get_exclusiveBetween() {
        ElementValueProvider provider = exclusiveBetween(0, 2);

        assertThat(collect(provider, 1000))
                .containsOnlyElementsOf(asList("0", "1"));
    }

    @Test
    public void get_inclusiveBetween() {
        ElementValueProvider provider = inclusiveBetween(0, 2);

        assertThat(collect(provider, 1000))
                .containsOnlyElementsOf(asList("0", "1", "2"));
    }
}