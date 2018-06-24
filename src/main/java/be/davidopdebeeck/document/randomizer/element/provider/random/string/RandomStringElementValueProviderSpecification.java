package be.davidopdebeeck.document.randomizer.element.provider.random.string;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;
import be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.random.RandomValueElementValueProvider;

public class RandomStringElementValueProviderSpecification
        implements ElementValueProviderSpecification<RandomValueElementValueMappingParameters> {

    @Override
    public ElementValueProvider createProvider(RandomValueElementValueMappingParameters parameters) {
        return RandomValueElementValueProvider.randomString(parameters.getLength());
    }

    @Override
    public RandomValueElementValueMappingParameters createDefaultParameters() {
        return new RandomValueElementValueMappingParameters(10);
    }
}
