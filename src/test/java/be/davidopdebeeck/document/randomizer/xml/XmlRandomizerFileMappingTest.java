package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.Randomizer;
import be.davidopdebeeck.document.randomizer.Randomizers;
import be.davidopdebeeck.document.randomizer.element.mapping.ElementValueMapping;
import io.reactivex.Flowable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesSequentialElementValueProvider.sequentialValues;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlRandomizerFileMappingTest {

    private Randomizer randomizer;

    @Before
    public void setUp() throws URISyntaxException {
        URL mappingsFile = ClassLoader.getSystemResource("mappings.txt");
        randomizer = Randomizers.xmlRandomizer(
                singletonList(new ElementValueMapping.Builder()
                        .withXpath("//*[local-name() = 'name']")
                        .withElementValueProvider(sequentialValues(new File(mappingsFile.toURI())))
                        .build()));
    }

    @Test
    public void randomize_withStringInputSource() {
        String document = randomizer.randomize(() -> Flowable.just(() -> "<name>David</name>"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    private void assertThatDocumentIsRandomized(String document) {
        assertThat(document).containsSubsequence("<name>test</name>");
    }
}