package be.davidopdebeeck.document.randomizer.element.provider;

import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;
import be.davidopdebeeck.document.randomizer.element.provider.mapping.ElementValueMappingParameters;

public interface ElementValueProviderSpecification<P extends ElementValueMappingParameters> {

    ElementValueProvider createProvider(P parameters);

    P createDefaultParameters();
}
