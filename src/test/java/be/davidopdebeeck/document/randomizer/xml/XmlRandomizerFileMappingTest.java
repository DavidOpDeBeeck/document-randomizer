package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.Randomizer;
import be.davidopdebeeck.document.randomizer.Randomizers;
import be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesElementValueProviderSpecification;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMapping;
import io.reactivex.Flowable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlRandomizerFileMappingTest {

    private Randomizer<XmlDocument> randomizer;

    @Before
    public void setUp() throws URISyntaxException {
        URL mappingsFile = ClassLoader.getSystemResource("mappings.txt");
        FileValuesElementValueProviderSpecification fileValuesSpecification = new FileValuesElementValueProviderSpecification();
        randomizer = Randomizers.xmlRandomizer(
                asList(new ElementValueMapping.Builder<>(fileValuesSpecification)
                        .withXpath("//*[local-name() = 'name']")
                        .withParameters(new FileValuesElementValueMappingParameters(new File(mappingsFile.toURI())))
                        .build()));
    }

    @Test
    public void randomize_withStringInputSource() {
        XmlDocument document = randomizer.randomize(() -> Flowable.just(() -> "<name>David</name>"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    private void assertThatDocumentIsRandomized(XmlDocument document) {
        assertThat(document.toString()).containsSubsequence("<name>test</name>");
    }
}