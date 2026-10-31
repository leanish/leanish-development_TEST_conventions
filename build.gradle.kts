plugins {
    `java-library`
    id("io.github.leanish.java-conventions")
}

group = "io.github.leanish.tests"
version = "1.0.0"
description = "Synthetic convention consumer for supply-chain end-to-end runs."

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

// Floors for checkstyle's libraries. Declared on a helper configuration that checkstyle extends,
// because declaring on checkstyle itself would replace the plugin's default checkstyle tool.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: CompactHashMap/CompactHashSet deserialization allocates unbounded memory; fixed in 33.7.2")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: CompactHashMap/CompactHashSet deserialization allocates unbounded memory; fixed in 33.7.2")
    }
    checkstyleFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: CompactHashMap/CompactHashSet deserialization allocates unbounded memory; fixed in 33.7.2")
    }
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: extractFile directory traversal; 3.6.1 is the fixed release of the 3.x line")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: uncontrolled recursion on long inputs; fixed in 3.18.0")
    }
}
