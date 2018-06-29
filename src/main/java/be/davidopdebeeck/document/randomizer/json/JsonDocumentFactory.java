package be.davidopdebeeck.document.randomizer.json;

import be.davidopdebeeck.document.randomizer.document.DocumentFactory;
import be.davidopdebeeck.document.randomizer.input.Input;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import static com.jayway.jsonpath.Option.AS_PATH_LIST;
import static com.jayway.jsonpath.Option.SUPPRESS_EXCEPTIONS;

public class JsonDocumentFactory implements DocumentFactory<JsonDocument> {

    @Override
    public <I extends Input> JsonDocument createDocument(I input) {
        return mapToJsonDocument(input);
    }

    private JsonDocument mapToJsonDocument(Input input) {
        return new JsonDocument(pathContext(input), mutableContext(input));
    }

    private DocumentContext pathContext(Input input) {
        return JsonPath.parse(input.getContent(), options(AS_PATH_LIST));
    }

    private DocumentContext mutableContext(Input input) {
        return JsonPath.parse(input.getContent(), options(SUPPRESS_EXCEPTIONS));
    }

    private Configuration options(Option options) {
        return Configuration.builder()
                .options(options)
                .build();
    }
}
