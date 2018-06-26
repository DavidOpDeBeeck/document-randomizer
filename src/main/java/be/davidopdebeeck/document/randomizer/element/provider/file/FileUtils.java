package be.davidopdebeeck.document.randomizer.element.provider.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;
import static java.util.stream.Collectors.toList;

public class FileUtils {

    static List<String> readFileLines(File file) {
        try (Stream<String> lines = Files.lines(file.toPath(), defaultCharset())) {
            return lines.collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(format("Failed to read file: %s", file.getAbsolutePath()));
        }
    }
}
