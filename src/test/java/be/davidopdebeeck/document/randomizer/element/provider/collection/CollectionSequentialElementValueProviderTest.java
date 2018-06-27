package be.davidopdebeeck.document.randomizer.element.provider.collection;

import be.davidopdebeeck.document.randomizer.StringWrapper;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.collection.CollectionSequentialElementValueProvider.sequentialValues;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionSequentialElementValueProviderTest {

    @Test
    public void get_withString() {
        ElementValueProvider provider = sequentialValues(asList("1", "2", "3"));

        assertThat(collect(provider, 7))
                .containsExactly("1", "2", "3", "1", "2", "3", "1");
    }

    @Test
    public void get_withObject() {
        ElementValueProvider provider = sequentialValues(asList(
                new StringWrapper("1"),
                new StringWrapper("2"),
                new StringWrapper("3")));

        assertThat(collect(provider, 7))
                .containsOnly("1", "2", "3", "1", "2", "3", "1");
    }
}