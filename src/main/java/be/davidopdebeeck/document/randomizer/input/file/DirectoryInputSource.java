package be.davidopdebeeck.document.randomizer.input.file;

import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class DirectoryInputSource implements InputSource<FileInput> {

    private final File directory;
    private final String fileExtension;

    public DirectoryInputSource(String path) {
        this.directory = new File(path);
        this.fileExtension = null;
    }

    public DirectoryInputSource(String path, String fileExtension) {
        this.directory = new File(path);
        this.fileExtension = fileExtension;
    }

    @Override
    public Flowable<FileInput> getSource() {
        return Flowable.fromIterable(findAllFilesWithExtension())
                .map(FileInput::new);
    }

    private List<File> findAllFilesWithExtension() {
        File[] filesWithExtension = directory.listFiles(this::hasExtension);
        return filesWithExtension == null ? emptyList() : asList(filesWithExtension);
    }

    private boolean hasExtension(File file) {
        return fileExtension == null || file.getName().endsWith(fileExtension);
    }
}
