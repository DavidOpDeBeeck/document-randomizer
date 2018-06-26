package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.Randomizer;
import be.davidopdebeeck.document.randomizer.Randomizers;
import be.davidopdebeeck.document.randomizer.StringWrapper;
import be.davidopdebeeck.document.randomizer.element.mapping.ElementValueMapping;
import be.davidopdebeeck.document.randomizer.input.CompositeInputSource;
import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.file.DirectoryInputSource;
import be.davidopdebeeck.document.randomizer.input.file.FileInputSource;
import be.davidopdebeeck.document.randomizer.input.iterable.IterableInputSource;
import io.reactivex.Flowable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonRandomizerTest {

    private Randomizer<JsonDocument> randomizer;

    @Before
    public void setUp() {
        randomizer = Randomizers.jsonRandomizer(
                singletonList(new ElementValueMapping.Builder()
                        .withXpath("$..name")
                        .withElementValueProvider(() -> "test")
                        .build()));
    }

    @Test
    public void randomize_withStringInputSource() {
        JsonDocument document = randomizer.randomize(() -> Flowable.just(() -> "{ \"name\": \"David\" }"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withMultipleStringsInputSource() {
        List<Input> inputs = asList(
                () -> "{ \"name\": \"David\" }",
                () -> "{ \"name\": \"David\" }",
                () -> "{ \"name\": \"David\" }");

        List<JsonDocument> documents = randomizer.randomize(() -> Flowable.fromIterable(inputs))
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withIterableInputSource_withString() {
        IterableInputSource inputSource = IterableInputSource.fromIterable(asList(
                "{ \"name\": \"David\" }", "{ \"name\": \"David\" }", "{ \"name\": \"David\" }"));

        List<JsonDocument> documents = randomizer.randomize(inputSource)
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withIterableInputSource_withObject() {
        IterableInputSource inputSource = IterableInputSource.fromIterable(asList(
                new StringWrapper("{ \"name\": \"David\" }"),
                new StringWrapper("{ \"name\": \"David\" }"),
                new StringWrapper("{ \"name\": \"David\" }")),
                StringWrapper::getContent);

        List<JsonDocument> documents = randomizer.randomize(inputSource)
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withDirectoryInputSource_withExtension() {
        URL jsonFilesDirectory = ClassLoader.getSystemResource("json-files");

        JsonDocument document = randomizer.randomize(new DirectoryInputSource(jsonFilesDirectory.getPath(), "json"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withDirectoryInputSource_withoutExtension() {
        URL jsonFilesDirectory = ClassLoader.getSystemResource("json-files");

        JsonDocument document = randomizer.randomize(new DirectoryInputSource(jsonFilesDirectory.getPath()))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withMultipleFilesInputSources() throws URISyntaxException {
        URL jsonFile1 = ClassLoader.getSystemResource("json-files/test1.json");
        URL jsonFile2 = ClassLoader.getSystemResource("json-files/test2.json");

        List<JsonDocument> documents = randomizer.randomize(
                new CompositeInputSource<>(
                        new FileInputSource(new File(jsonFile1.toURI())),
                        new FileInputSource(new File(jsonFile2.toURI()))))
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
    }

    private void assertThatDocumentIsRandomized(JsonDocument document) {
        assertThat(document.toString()).containsSubsequence("{\"name\":\"test\"}");
    }
}