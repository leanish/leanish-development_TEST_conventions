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

// Checkstyle's tool is a plugin default, so its floors sit in a helper
// configuration that checkstyle extends.
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below 33.7.2-jre on the annotation processor path")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below 33.7.2-jre on the test annotation processor path")
    }
    "checkstyleSecurityFloors"("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below 33.7.2-jre pulled in by the checkstyle tool")
    }
    "checkstyleSecurityFloors"("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils below 3.6.1 pulled in by the checkstyle tool")
    }
    "checkstyleSecurityFloors"("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 below 3.18.0 pulled in by the checkstyle tool")
    }
}
