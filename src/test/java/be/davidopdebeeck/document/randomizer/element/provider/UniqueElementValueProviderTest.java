package be.davidopdebeeck.document.randomizer.element.provider;

import org.junit.Test;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.UniqueElementValueProvider.onlyUniqueValues;
import static be.davidopdebeeck.document.randomizer.element.provider.collection.CollectionSequentialElementValueProvider.sequentialValues;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UniqueElementValueProviderTest {

    @Test
    public void get_onlyUniqueValues() {
        UniqueElementValueProvider provider = onlyUniqueValues(sequentialValues(asList("1", "1", "2")));

        assertThat(collect(provider, 2))
                .containsExactly("1", "2");
    }

    @Test
    public void get_onlyUniqueValues_crashesWhenNoUniqueElementAvailable() {
        UniqueElementValueProvider provider = onlyUniqueValues(sequentialValues(asList("1", "1", "2")));

        assertThatThrownBy(() -> collect(provider, 3))
                .isInstanceOf(ElementValueProviderFailedException.class);
    }
}