package be.davidopdebeeck.document.randomizer.element;

public class SingleElement implements Element {

    private final Element element;

    public SingleElement(Element element) {
        this.element = element;
    }

    @Override
    public void setValue(String value) {
        element.setValue(value);
    }

    @Override
    public String getValue() {
        return element.getValue();
    }
}
