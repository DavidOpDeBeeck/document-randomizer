package be.davidopdebeeck.document.randomizer.metadata.file;

import be.davidopdebeeck.document.randomizer.input.file.FileInput;
import be.davidopdebeeck.document.randomizer.metadata.Metadata;

import java.io.File;

public class FileMetadata implements Metadata {

    private final File file;

    public FileMetadata(FileInput input) {
        this.file = input.getFile();
    }

    public File getFile() {
        return file;
    }

    public String getFilename() {
        return file.getName();
    }
}
