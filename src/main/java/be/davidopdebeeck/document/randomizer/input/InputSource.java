package be.davidopdebeeck.document.randomizer.input;

import io.reactivex.Flowable;

public interface InputSource<I extends Input> {

    Flowable<I> getSource();
}
