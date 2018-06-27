package be.davidopdebeeck.document.randomizer.element.provider.random;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import java.util.regex.Pattern;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.random.RandomNumberBetweenElementValueProvider.exclusiveBetween;
import static be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueProvider.randomNumber;
import static be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueProvider.randomString;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class RandomValueElementValueProviderTest {

    @Test
    public void get_randomString() {
        ElementValueProvider provider = randomString(10);

        String value = provider.get();

        assertThat(value).hasSize(10);
        assertThat(value).doesNotContainPattern(compile("[0-9]+"));
    }

    @Test
    public void get_randomNumber() {
        ElementValueProvider provider = randomNumber(10);

        String value = provider.get();

        assertThat(value).hasSize(10);
        assertThat(value).containsOnlyDigits();
    }
}