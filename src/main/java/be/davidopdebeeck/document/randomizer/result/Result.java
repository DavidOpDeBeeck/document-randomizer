package be.davidopdebeeck.document.randomizer.result;

import be.davidopdebeeck.document.randomizer.metadata.Metadata;

public class Result<M extends Metadata> {

    private final M metadata;
    private final String content;

    public Result(M metadata, String content) {
        this.metadata = metadata;
        this.content = content;
    }

    public M getMetadata() {
        return metadata;
    }

    public String getContent() {
        return content;
    }
}
