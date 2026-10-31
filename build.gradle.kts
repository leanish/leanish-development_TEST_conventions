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

// Checkstyle's tool is only filled with defaults while its own dependencies are empty,
// so the checkstyle floors go in a helper configuration that checkstyle extends.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.getByName("checkstyle").extendsFrom(checkstyleFloors)

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is vulnerable")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is vulnerable")
    }
    checkstyleFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is vulnerable")
    }
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils 3.3.0 is vulnerable")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 3.8.1 is vulnerable")
    }
}
