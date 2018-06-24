package be.davidopdebeeck.document.randomizer.element.provider.random;

import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;

public class RandomValueElementValueMappingParameters implements ElementValueMappingParameters {

    private final int length;

    public RandomValueElementValueMappingParameters(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
