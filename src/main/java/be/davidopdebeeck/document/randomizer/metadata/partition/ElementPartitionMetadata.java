package be.davidopdebeeck.document.randomizer.metadata.partition;

import be.davidopdebeeck.document.randomizer.metadata.Metadata;

public class ElementPartitionMetadata implements Metadata {

    private final int elementIndex;
    private final int partitionIndex;

    ElementPartitionMetadata(int elementIndex, int partitionIndex) {
        this.elementIndex = elementIndex;
        this.partitionIndex = partitionIndex;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public int getPartitionIndex() {
        return partitionIndex;
    }
}
