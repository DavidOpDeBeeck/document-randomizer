package be.davidopdebeeck.document.randomizer.element.provider.random.number.between;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.Random;

public class RandomNumberBetweenElementValueProvider implements ElementValueProvider {

    private final Random random = new Random();
    private final int lowerBound;
    private final int upperBound;

    RandomNumberBetweenElementValueProvider(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public String get() {
        return "" + (random.nextInt(upperBound - lowerBound) + lowerBound);
    }
}
