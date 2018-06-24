package be.davidopdebeeck.document.randomizer.input.file;

import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

import java.io.File;

public class FileInputSource implements InputSource<FileInput> {

    private final File file;

    public FileInputSource(File file) {
        this.file = file;
    }

    @Override
    public Flowable<FileInput> getSource() {
        return Flowable.just(new FileInput(file));
    }
}
