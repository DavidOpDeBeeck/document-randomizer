package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.Randomizers;
import be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.file.FileValuesElementValueProviderSpecification;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMapping;
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
import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonRandomizerFileMappingTest {

    private Randomizer<JsonDocument> randomizer;

    @Before
    public void setUp() throws URISyntaxException {
        URL mappingsFile = ClassLoader.getSystemResource("mappings.txt");
        FileValuesElementValueProviderSpecification fileValuesSpecification = new FileValuesElementValueProviderSpecification();
        randomizer = Randomizers.jsonRandomizer(
                Arrays.asList(new ElementValueMapping.Builder<>(fileValuesSpecification)
                        .withXpath("$..name")
                        .withParameters(new FileValuesElementValueMappingParameters(new File(mappingsFile.toURI())))
                        .build()));
    }

    @Test
    public void randomize_withStringInputSource() {
        JsonDocument document = randomizer.randomize(() -> Flowable.just(() -> "{ \"name\": \"David\" }"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    private void assertThatDocumentIsRandomized(JsonDocument document) {
        assertThat(document.toString()).containsSubsequence("{\"name\":\"test\"}");
    }
}