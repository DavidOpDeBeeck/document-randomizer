package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import be.davidopdebeeck.document.randomizer.document.Document;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import io.reactivex.Flowable;

public interface Randomizer<D extends Document> {

    Flowable<D> randomize(InputSource<?> inputSource);
}
