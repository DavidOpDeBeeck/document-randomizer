package be.davidopdebeeck.document.randomizer.element.provider.collection;

import be.davidopdebeeck.document.randomizer.StringWrapper;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.collection.CollectionRandomElementValueProvider.randomValues;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionRandomElementValueProviderTest {

    @Test
    public void get_withString() {
        ElementValueProvider provider = randomValues(asList("1", "2", "3"));

        assertThat(collect(provider, 100))
            .containsOnlyElementsOf(asList("1", "2", "3"));
    }

    @Test
    public void get_withObject() {
        ElementValueProvider provider = randomValues(asList(
                new StringWrapper("1"),
                new StringWrapper("2"),
                new StringWrapper("3")));

        assertThat(collect(provider, 100))
            .containsOnlyElementsOf(asList("1", "2", "3"));
    }
}