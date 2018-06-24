package be.davidopdebeeck.document.randomizer.element.provider.file;

import java.util.List;

public class FileMapping {

    private final String identifier;
    private final List<String> values;

    public FileMapping(String identifier, List<String> values) {
        this.identifier = identifier;
        this.values = values;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getValues() {
        return values;
    }
}
