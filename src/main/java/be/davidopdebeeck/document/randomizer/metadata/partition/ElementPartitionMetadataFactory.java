package be.davidopdebeeck.document.randomizer.metadata.partition;

import be.davidopdebeeck.document.randomizer.input.Input;
import be.davidopdebeeck.document.randomizer.metadata.MetadataFactory;

public class ElementPartitionMetadataFactory<I extends Input> implements MetadataFactory<ElementPartitionMetadata, I> {

    private int counter;
    private final int partitionSize;

    public ElementPartitionMetadataFactory(int partitionSize) {
        this.counter = 0;
        this.partitionSize = partitionSize;
    }

    @Override
    public ElementPartitionMetadata createMetadata(I input) {
        int elementIndex = this.counter++;
        int partitionIndex = calculateElementPartition(elementIndex);
        return new ElementPartitionMetadata(elementIndex, partitionIndex);
    }

    private int calculateElementPartition(int itemIndex) {
        return (int) Math.ceil((double) itemIndex / (double) partitionSize);
    }
}
