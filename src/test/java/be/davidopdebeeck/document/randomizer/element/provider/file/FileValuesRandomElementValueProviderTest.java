package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesRandomElementValueProvider.randomValues;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class FileValuesRandomElementValueProviderTest {

    @Test
    public void get() throws URISyntaxException {
        URL fileUrl = ClassLoader.getSystemResource("file/file1.txt");
        ElementValueProvider provider = randomValues(new File(fileUrl.toURI()));

        assertThat(collect(provider, 100))
                .containsOnlyElementsOf(asList("1", "2", "3"));
    }
}