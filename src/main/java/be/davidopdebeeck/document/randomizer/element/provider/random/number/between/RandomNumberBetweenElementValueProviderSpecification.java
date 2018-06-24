package be.davidopdebeeck.document.randomizer.element.provider.random.number.between;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;

public class RandomNumberBetweenElementValueProviderSpecification
        implements ElementValueProviderSpecification<RandomNumberBetweenElementValueMappingParameters> {

    @Override
    public ElementValueProvider createProvider(RandomNumberBetweenElementValueMappingParameters parameters) {
        return new RandomNumberBetweenElementValueProvider(parameters.getLowerBound(), parameters.getUpperBound());
    }

    @Override
    public RandomNumberBetweenElementValueMappingParameters createDefaultParameters() {
        return new RandomNumberBetweenElementValueMappingParameters(0, 10);
    }
}
