package be.davidopdebeeck.document.randomizer.element.provider.random.number.between;

import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;

public class RandomNumberBetweenElementValueMappingParameters implements ElementValueMappingParameters {

    private final int lowerBound;
    private final int upperBound;

    public RandomNumberBetweenElementValueMappingParameters(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
