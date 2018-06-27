package be.davidopdebeeck.document.randomizer.element.mapping;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;
import be.davidopdebeeck.document.randomizer.element.provider.UniqueElementValueProvider;

import static be.davidopdebeeck.document.randomizer.element.provider.UniqueElementValueProvider.onlyUniqueValues;
import static java.util.Objects.requireNonNull;

public class ElementValueMapping {

    private final String xpath;
    private final boolean groupNodesWithSameText;
    private final ElementValueProvider elementValueProvider;

    private ElementValueMapping(String xpath,
                                boolean groupNodesWithSameText,
                                ElementValueProvider elementValueProvider) {
        this.xpath = xpath;
        this.groupNodesWithSameText = groupNodesWithSameText;
        this.elementValueProvider = elementValueProvider;
    }

    public String getXpath() {
        return xpath;
    }

    public boolean groupNodesWithSameText() {
        return groupNodesWithSameText;
    }

    public ElementValueProvider getElementValueProvider() {
        return elementValueProvider;
    }

    public static class Builder {

        private String xpath;
        private boolean unique = false;
        private boolean groupNodesWithSameText = true;
        private ElementValueProvider elementValueProvider;

        public Builder withXpath(String xpath) {
            this.xpath = xpath;
            return this;
        }

        public Builder withUnique(boolean unique) {
            this.unique = unique;
            return this;
        }

        public Builder withGroupNodesWithSameText(boolean groupNodesWithSameText) {
            this.groupNodesWithSameText = groupNodesWithSameText;
            return this;
        }

        public Builder withElementValueProvider(ElementValueProvider elementValueProvider) {
            this.elementValueProvider = elementValueProvider;
            return this;
        }

        public ElementValueMapping build() {
            requireNonNull(xpath);
            requireNonNull(elementValueProvider);
            return new ElementValueMapping(xpath, groupNodesWithSameText, elementValueProvider());
        }

        private ElementValueProvider elementValueProvider() {
            return unique ? onlyUniqueValues(elementValueProvider) : elementValueProvider;
        }
    }
}
