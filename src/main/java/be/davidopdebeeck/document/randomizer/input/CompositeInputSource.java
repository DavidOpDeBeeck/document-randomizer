package be.davidopdebeeck.document.randomizer.input;

import io.reactivex.Flowable;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class CompositeInputSource<I extends Input> implements InputSource<I> {

    private final List<InputSource<I>> inputSources;

    @SafeVarargs
    public CompositeInputSource(InputSource<I>... inputSources) {
        this(asList(inputSources));
    }

    public CompositeInputSource(List<InputSource<I>> inputSources) {
        requireNonNull(inputSources);
        this.inputSources = inputSources;
    }

    @Override
    public Flowable<I> getSource() {
        return Flowable.merge(inputSources.stream()
                .map(InputSource::getSource)
                .collect(Collectors.toList()));
    }
}
