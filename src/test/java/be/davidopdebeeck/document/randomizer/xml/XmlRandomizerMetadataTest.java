package be.davidopdebeeck.document.randomizer.xml;

import be.davidopdebeeck.document.randomizer.Randomizer;
import be.davidopdebeeck.document.randomizer.Randomizers;
import be.davidopdebeeck.document.randomizer.element.mapping.ElementValueMapping;
import be.davidopdebeeck.document.randomizer.input.CompositeInputSource;
import be.davidopdebeeck.document.randomizer.input.file.FileInput;
import be.davidopdebeeck.document.randomizer.input.file.FileInputSource;
import be.davidopdebeeck.document.randomizer.metadata.file.FileMetadata;
import be.davidopdebeeck.document.randomizer.metadata.partition.ElementPartitionMetadata;
import be.davidopdebeeck.document.randomizer.metadata.partition.ElementPartitionMetadataFactory;
import be.davidopdebeeck.document.randomizer.result.Result;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlRandomizerMetadataTest {

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
    public void randomize_withFileMetadata() throws URISyntaxException {
        URL xmlFile1 = ClassLoader.getSystemResource("xml-files/test1.xml");
        URL xmlFile2 = ClassLoader.getSystemResource("xml-files/test2.xml");
        CompositeInputSource<FileInput> inputSource = new CompositeInputSource<>(
                new FileInputSource(new File(xmlFile1.toURI())),
                new FileInputSource(new File(xmlFile2.toURI())));

        List<Result<FileMetadata>> documents = randomizer.randomize(inputSource, FileMetadata::new)
                .toList().blockingGet();

        Result<FileMetadata> result1 = documents.get(0);
        Result<FileMetadata> result2 = documents.get(1);
        assertThat(result1.getMetadata().getFilename()).isEqualTo("test1.xml");
        assertThatDocumentIsRandomized(result1.getContent());
        assertThat(result2.getMetadata().getFilename()).isEqualTo("test2.xml");
        assertThatDocumentIsRandomized(result2.getContent());
    }

    @Test
    public void randomize_withElementPartitionMetadata() throws URISyntaxException {
        URL xmlFile1 = ClassLoader.getSystemResource("xml-files/test1.xml");
        URL xmlFile2 = ClassLoader.getSystemResource("xml-files/test2.xml");
        CompositeInputSource<FileInput> inputSource = new CompositeInputSource<>(
                new FileInputSource(new File(xmlFile1.toURI())),
                new FileInputSource(new File(xmlFile2.toURI())));

        List<Result<ElementPartitionMetadata>> documents = randomizer.randomize(inputSource, new ElementPartitionMetadataFactory<>(1))
                .toList().blockingGet();

        Result<ElementPartitionMetadata> result1 = documents.get(0);
        Result<ElementPartitionMetadata> result2 = documents.get(1);
        assertThat(result1.getMetadata().getElementIndex()).isEqualTo(0);
        assertThat(result1.getMetadata().getPartitionIndex()).isEqualTo(0);
        assertThatDocumentIsRandomized(result1.getContent());
        assertThat(result2.getMetadata().getElementIndex()).isEqualTo(1);
        assertThat(result2.getMetadata().getPartitionIndex()).isEqualTo(1);
        assertThatDocumentIsRandomized(result2.getContent());
    }

    private void assertThatDocumentIsRandomized(String document) {
        assertThat(document).containsSubsequence("<name>test</name>");
    }
}