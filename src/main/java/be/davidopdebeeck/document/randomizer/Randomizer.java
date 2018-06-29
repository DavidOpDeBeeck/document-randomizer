package be.davidopdebeeck.document.randomizer;

import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.input.InputSource;
import be.davidopdebeeck.document.randomizer.metadata.Metadata;
import be.davidopdebeeck.document.randomizer.metadata.MetadataFactory;
import be.davidopdebeeck.document.randomizer.result.Result;
import io.reactivex.Flowable;

public interface Randomizer {

    Flowable<String> randomize(InputSource<?> inputSource);

    <M extends Metadata, I extends Input> Flowable<Result<M>> randomize(InputSource<I> inputSource, MetadataFactory<M, I> metadataFactory);
}
