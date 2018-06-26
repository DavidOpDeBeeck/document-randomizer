package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

public interface Randomizer {

    Flowable<String> randomize(InputSource<?> inputSource);
}
