package be.davidopdebeeck.document.randomizer.element.provider.mapping;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProviderSpecification;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("unused")
public class ElementValueMapping {

    private final String xpath;
    private final boolean unique;
    private final boolean groupNodesWithSameText;
    private final ElementValueProvider elementValueProvider;

    private ElementValueMapping(String xpath,
                                boolean unique,
                                boolean groupNodesWithSameText,
                                ElementValueProvider elementValueProvider) {
        this.xpath = xpath;
        this.unique = unique;
        this.groupNodesWithSameText = groupNodesWithSameText;
        this.elementValueProvider = elementValueProvider;
    }

    public String getXpath() {
        return xpath;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean groupNodesWithSameText() {
        return groupNodesWithSameText;
    }

    public ElementValueProvider getElementValueProvider() {
        return elementValueProvider;
    }

    public static class Builder<P extends ElementValueMappingParameters, S extends ElementValueProviderSpecification<P>> {

        private final S specification;

        private P parameters;
        private String xpath;
        private boolean unique = false;
        private boolean groupNodesWithSameText = true;

        public Builder(S specification) {
            this.specification = specification;
        }

        public Builder<P, S> withParameters(P parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder<P, S> withXpath(String xpath) {
            this.xpath = xpath;
            return this;
        }

        public Builder<P, S> withUnique(boolean unique) {
            this.unique = unique;
            return this;
        }

        public Builder<P, S> withGroupNodesWithSameText(boolean groupNodesWithSameText) {
            this.groupNodesWithSameText = groupNodesWithSameText;
            return this;
        }

        public ElementValueMapping build() {
            requireNonNull(xpath);
            requireNonNull(specification);
            return new ElementValueMapping(xpath, unique, groupNodesWithSameText, createValueProvider());
        }

        private ElementValueProvider createValueProvider() {
            P parameters = getParameters(specification);
            requireNonNull(parameters);
            return specification.createProvider(parameters);
        }

        private P getParameters(S specification) {
            return parameters == null ? specification.createDefaultParameters() : parameters;
        }
    }
}
