package be.davidopdebeeck.document.randomizer.element.provider.random;

import be.davidopdebeeck.document.randomizer.element.provider.ElementValueProvider;

import java.util.Random;

public class RandomValueElementValueProvider implements ElementValueProvider {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";

    public static RandomValueElementValueProvider randomString(int length) {
        return new RandomValueElementValueProvider(length, LETTERS.toCharArray());
    }

    public static RandomValueElementValueProvider randomNumber(int length) {
        return new RandomValueElementValueProvider(length, NUMBERS.toCharArray());
    }

    private final int length;
    private final char[] chars;
    private final Random random = new Random();

    private RandomValueElementValueProvider(int length, char[] chars) {
        this.length = length;
        this.chars = chars;
    }

    @Override
    public String get() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
