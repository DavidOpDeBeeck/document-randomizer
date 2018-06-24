package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;
import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.element.Element;
import com.jayway.jsonpath.DocumentContext;
import net.minidev.json.JSONArray;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JsonDocument implements Document {

    private final DocumentContext pathContext;
    private final DocumentContext documentContext;

    JsonDocument(DocumentContext pathContext, DocumentContext documentContext) {
        this.pathContext = pathContext;
        this.documentContext = documentContext;
    }

    @Override
    public List<Element> findElementsBy(String xpath) {
        return findNodesPaths(xpath).stream()
                .map(nodePath -> new JsonElement(documentContext, nodePath.toString()))
                .collect(toList());
    }

    @Override
    public String toString() {
        return documentContext.jsonString();
    }

    private List<Object> findNodesPaths(String xpath) {
        return (JSONArray) pathContext.read(xpath, List.class);
    }
}
