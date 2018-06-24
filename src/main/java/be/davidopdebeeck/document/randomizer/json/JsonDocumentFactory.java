package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.reactivex.Flowable;

import static com.jayway.jsonpath.Option.AS_PATH_LIST;
import static com.jayway.jsonpath.Option.SUPPRESS_EXCEPTIONS;

public class JsonDocumentFactory implements DocumentFactory<JsonDocument> {

    @Override
    public Flowable<JsonDocument> createDocument(InputSource<?> source) {
        return source.getSource().map(this::mapToJsonDocument);
    }

    private JsonDocument mapToJsonDocument(Input input) {
        return new JsonDocument(pathContext(input), mutableContext(input));
    }

    private DocumentContext pathContext(Input input) {
        return JsonPath.parse(input.getContent(), Configuration.builder()
                .options(AS_PATH_LIST)
                .build());
    }

    private DocumentContext mutableContext(Input input) {
        return JsonPath.parse(input.getContent(), Configuration.builder()
                .options(SUPPRESS_EXCEPTIONS)
                .build());
    }
}
