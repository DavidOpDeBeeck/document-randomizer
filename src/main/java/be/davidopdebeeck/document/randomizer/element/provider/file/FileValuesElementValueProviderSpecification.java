package be.davidopdebeeck.document.randomizer.element.provider.file;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.toList;

public class FileValuesElementValueProviderSpecification implements ElementValueProviderSpecification<FileValuesElementValueMappingParameters> {

    @Override
    public ElementValueProvider createProvider(FileValuesElementValueMappingParameters parameters) {
        return mapToProvider(parameters, getFileLines(parameters.getFile()));
    }

    @Override
    public FileValuesElementValueMappingParameters createDefaultParameters() {
        return null;
    }

    private ElementValueProvider mapToProvider(FileValuesElementValueMappingParameters parameters, List<String> values) {
        return parameters.isRandom()
                ? new FileValuesRandomElementValueProvider(values)
                : new FileValuesSequentialElementValueProvider(values);
    }

    private List<String> getFileLines(File file) {
        try (Stream<String> lines = Files.lines(file.toPath(), defaultCharset())) {
            return lines.collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(format("Failed to read file %s", file.getAbsolutePath()));
        }
    }
}
