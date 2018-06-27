package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderCollector.collect;
import static be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesSequentialElementValueProvider.sequentialValues;
import static org.assertj.core.api.Assertions.assertThat;

public class FileValuesSequentialElementValueProviderTest {

    @Test
    public void get() throws URISyntaxException {
        URL fileUrl = ClassLoader.getSystemResource("file/file1.txt");
        ElementValueProvider provider = sequentialValues(new File(fileUrl.toURI()));

        assertThat(collect(provider, 7))
                .containsExactly("1", "2", "3", "1", "2", "3", "1");
    }
}