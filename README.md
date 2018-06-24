## Gradle

```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/davidopdebeeck/open-source" 
    }
}

dependencies {
    compile 'be.davidopdebeeck:document-randomizer:1.0'
}
```

## Purpose

The purpose of this library is to provide an easy way to "randomize" fields in a XML or JSON document. A perfect use case would be the anonymization of production data used in development environments.

## Usage

The randomizer can be configured with _element mappings_ and _element value specifications_.

#### Element mappings

An element mapping specifies which elements in the document will be randomized and how they will be randomized. Currently you can configure the following properties.

- __xpath__: specifies which elements will be selected
- __unique__: specifies if the new element value needs to be unique (unique in this mapping and all the other documents)
- __groupNodesWithSameText__: specifies if nodes with the same value are given the same new element value
- __specification__: specifies how the new element value is generated
- __parameters__: specifies extra parameters for the specification

#### Element value specifications

Specifications define how the new element values are generated. The following specifications are supplied by the library.

- __RandomNumberElementValueProviderSpecification__
- __RandomNumberBetweenElementValueProviderSpecification__
- __RandomStringElementValueProviderSpecification__
- __FileValuesElementValueProviderSpecification__

## Example

#### Input

```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
    <name>David</name>
</root>
```

#### Java code

```java
class Example {
    
    public static void main(String[] args){
        Randomizer<XmlDocument> randomizer = createRandomizer();
        
        randomizer.randomize(new FileInputSource(new File("path/to/xml/file.xml")))
            .subscribe(System.out::println);
    }
    
    private static Randomizer<XmlDocument> createRandomizer() {
        return Randomizers.xmlRandomizer(
                asList(new ElementValueMapping.Builder<>(new RandomStringElementValueProviderSpecification())
                        .withXpath("//*[local-name() = 'name']")
                        .build()));
    }
}
```

#### Output

```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
    <name>kofqwkshjf</name>
</root>
```

## Supported documents

XML and JSON are the currently supported document languages. Feel free to contribute other document languages.