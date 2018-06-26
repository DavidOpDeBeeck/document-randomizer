package be.davidopdebeeck.document.randomizer.xml;

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

public class XmlRandomizerTest {

    private Randomizer randomizer;

    @Before
    public void setUp() {
        randomizer = Randomizers.xmlRandomizer(
                singletonList(new ElementValueMapping.Builder()
                        .withXpath("//*[local-name() = 'name']")
                        .withElementValueProvider(() -> "test")
                        .build()));
    }

    @Test
    public void randomize_withStringInputSource() {
        String document = randomizer.randomize(() -> Flowable.just(() -> "<name>David</name>"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withMultipleStringsInputSource() {
        List<Input> inputs = asList(
                () -> "<name>David</name>",
                () -> "<name>David</name>",
                () -> "<name>David</name>");

        List<String> documents = randomizer.randomize(() -> Flowable.fromIterable(inputs))
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withIterableInputSource_withString() {
        IterableInputSource inputSource = IterableInputSource.fromIterable(asList(
                "<name>David</name>", "<name>David</name>", "<name>David</name>"));

        List<String> documents = randomizer.randomize(inputSource)
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withIterableInputSource_withObject() {
        IterableInputSource inputSource = IterableInputSource.fromIterable(asList(
                new StringWrapper("<name>David</name>"),
                new StringWrapper("<name>David</name>"),
                new StringWrapper("<name>David</name>")),
                StringWrapper::getContent);

        List<String> documents = randomizer.randomize(inputSource)
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
        assertThatDocumentIsRandomized(documents.get(2));
    }

    @Test
    public void randomize_withDirectoryInputSource_withExtension() {
        URL xmlFilesDirectory = ClassLoader.getSystemResource("xml-files");

        String document = randomizer.randomize(new DirectoryInputSource(xmlFilesDirectory.getPath(), "xml"))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withDirectoryInputSource_withoutExtension() {
        URL xmlFilesDirectory = ClassLoader.getSystemResource("xml-files");

        String document = randomizer.randomize(new DirectoryInputSource(xmlFilesDirectory.getPath()))
                .blockingFirst();

        assertThatDocumentIsRandomized(document);
    }

    @Test
    public void randomize_withMultipleFilesInputSources() throws URISyntaxException {
        URL xmlFile1 = ClassLoader.getSystemResource("xml-files/test1.xml");
        URL xmlFile2 = ClassLoader.getSystemResource("xml-files/test2.xml");

        List<String> documents = randomizer.randomize(
                new CompositeInputSource<>(
                        new FileInputSource(new File(xmlFile1.toURI())),
                        new FileInputSource(new File(xmlFile2.toURI()))))
                .toList().blockingGet();

        assertThatDocumentIsRandomized(documents.get(0));
        assertThatDocumentIsRandomized(documents.get(1));
    }

    private void assertThatDocumentIsRandomized(String document) {
        assertThat(document).containsSubsequence("<name>test</name>");
    }
}