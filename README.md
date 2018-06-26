## Project Usage

### Gradle

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

## Library Usage

The randomizer can be configured with _element mappings_ and _element value providers_.

#### Element mappings

An element mapping specifies which elements in the document will be randomized and how they will be randomized. Currently you can configure the following properties.

- __xpath__: specifies which elements will be selected
- __unique__: specifies if the new element value needs to be unique (unique in this mapping and all the other documents)
- __groupNodesWithSameText__: specifies if nodes with the same value are given the same new element value
- __value provider__: specifies how the new element value is generated

#### Element value providers

Providers define how the new element values are generated. The following providers are supplied by the library.

- __RandomNumberBetweenElementValueProvider__
- __RandomValueElementValueProvider__
- __FileValuesRandomElementValueProvider__
- __FileValuesSequentialElementValueProvider__
- __CollectionRandomElementValueProvider__
- __CollectionSequentialElementValueProvider__

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
        Randomizer randomizer = createXmlRandomizer();
        
        randomizer.randomize(new FileInputSource(new File("path/to/xml/file.xml")))
            .subscribe(System.out::println);
    }
    
    private static Randomizer createXmlRandomizer() {
        return Randomizers.xmlRandomizer(
                asList(new ElementValueMapping.Builder()
                        .withXpath("//*[local-name() = 'name']")
                        .withElementValueProvider(randomString(10))
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