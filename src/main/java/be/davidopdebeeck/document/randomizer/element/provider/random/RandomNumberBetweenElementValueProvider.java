package be.davidopdebeeck.document.randomizer.element.provider.random;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.Random;

public class RandomNumberBetweenElementValueProvider implements ElementValueProvider {

    public static ElementValueProvider exclusiveBetween(int lowerBound, int upperBound) {
        return new RandomNumberBetweenElementValueProvider(lowerBound, upperBound);
    }

    public static ElementValueProvider inclusiveBetween(int lowerBound, int upperBound) {
        return new RandomNumberBetweenElementValueProvider(lowerBound, upperBound + 1);
    }

    private final Random random = new Random();
    private final int lowerBound;
    private final int upperBound;

    private RandomNumberBetweenElementValueProvider(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public String get() {
        return "" + (random.nextInt(upperBound - lowerBound) + lowerBound);
    }
}
