package be.davidopdebeeck.document.randomizer.input.file;

import be.davidopdebeeck.document.randomizer.input.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;

public class FileInput implements Input {

    private static final String EMPTY = "";

    private final File file;

    public FileInput(File file) {
        this.file = file;
    }

    @Override
    public String getContent() {
        try (Stream<String> lines = Files.lines(file.toPath(), defaultCharset())) {
            return lines.collect(Collectors.joining(EMPTY));
        } catch (IOException e) {
            throw new RuntimeException(format("Failed to read file %s", file.getAbsolutePath()));
        }
    }
}
