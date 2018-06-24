package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.DefaultElementValueMappingParameters;

public class TestSpecification implements ElementValueProviderSpecification<DefaultElementValueMappingParameters> {

    @Override
    public ElementValueProvider createProvider(DefaultElementValueMappingParameters parameters) {
        return () -> "test";
    }

    @Override
    public DefaultElementValueMappingParameters createDefaultParameters() {
        return new DefaultElementValueMappingParameters();
    }
}