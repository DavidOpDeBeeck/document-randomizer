package be.davidopdebeeck.document.randomizer.element.provider.random.number;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;
import be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueProvider;

public class RandomNumberElementValueProviderSpecification
        implements ElementValueProviderSpecification<RandomValueElementValueMappingParameters> {

    @Override
    public ElementValueProvider createProvider(RandomValueElementValueMappingParameters parameters) {
        return RandomValueElementValueProvider.randomNumber(parameters.getLength());
    }

    @Override
    public RandomValueElementValueMappingParameters createDefaultParameters() {
        return new RandomValueElementValueMappingParameters(10);
    }
}
