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

// Checkstyle supplies its tool only while its own configuration is empty, so the floors go in a helper it extends.
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: directory traversal in Expand.extractFile; checkstyle resolved 3.3.0")
    }
    checkstyleSecurityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: DoS via eager array allocation in deserialization; checkstyle resolved 33.5.0-jre")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: uncontrolled recursion in ClassUtils.getClass on long inputs; checkstyle resolved 3.8.1")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: DoS via eager array allocation in deserialization; resolved 33.5.0-jre")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: DoS via eager array allocation in deserialization; resolved 33.5.0-jre")
    }
}
