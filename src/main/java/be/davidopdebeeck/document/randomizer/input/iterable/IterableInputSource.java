package be.davidopdebeeck.document.randomizer.input.iterable;

import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

import java.util.function.Function;

public class IterableInputSource<O> implements InputSource<Input> {

    public static IterableInputSource fromIterable(Iterable<String> iterable) {
        return new IterableInputSource<>(iterable, i -> i);
    }

    public static <O> IterableInputSource fromIterable(Iterable<O> iterable, Function<O, String> mapping) {
        return new IterableInputSource<>(iterable, mapping);
    }

    private final Iterable<O> iterable;
    private final Function<O, String> mapping;

    private IterableInputSource(Iterable<O> iterable, Function<O, String> mapping) {
        this.iterable = iterable;
        this.mapping = mapping;
    }

    @Override
    public Flowable<Input> getSource() {
        return Flowable.fromIterable(iterable)
                .map(el -> (Input) () -> mapping.apply(el));
    }
}
