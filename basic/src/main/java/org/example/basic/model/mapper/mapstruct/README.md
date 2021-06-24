# MapStruct

## IDE 集成

https://plugins.jetbrains.com/plugin/10036-mapstruct-support

## Maven 依赖

```xml
...
<properties>
    <org.mapstruct.version>1.4.2.Final</org.mapstruct.version>
</properties>
        ...
<dependencies>
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>${org.mapstruct.version}</version>
</dependency>
</dependencies>
        ...
<build>
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
            <annotationProcessorPaths>
                <path>
                    <groupId>org.mapstruct</groupId>
                    <artifactId>mapstruct-processor</artifactId>
                    <version>${org.mapstruct.version}</version>
                </path>
            </annotationProcessorPaths>
        </configuration>
    </plugin>
</plugins>
</build>
        ...

```

## 注意事项

mapstrcut 与 lombok 有冲突。

* https://github.com/mapstruct/mapstruct