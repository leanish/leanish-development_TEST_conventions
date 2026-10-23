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

val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.checkstyle {
    extendsFrom(checkstyleFloors)
}

dependencies {
    "checkstyleFloors"("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: checkstyle's doxia path resolves plexus-utils 3.3.0; 3.6.1 is the fixed version")
    }
    "checkstyleFloors"("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: checkstyle resolves guava 33.5.0-jre; 33.7.2-jre is the fixed version")
    }
    "checkstyleFloors"("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: checkstyle's doxia path resolves commons-lang3 3.8.1; 3.18.0 is the fixed version")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: error-prone resolves guava 33.5.0-jre; 33.7.2-jre is the fixed version")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: error-prone resolves guava 33.5.0-jre; 33.7.2-jre is the fixed version")
    }
}
