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

// The checkstyle plugin fills the `checkstyle` configuration with its tool only while it has no
// dependencies, so the security floors live in a helper configuration that `checkstyle` extends.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor to the fixed 33.7.2-jre for the annotation processor path")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor to the fixed 33.7.2-jre for the test annotation processor path")
    }
    checkstyleFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor to the fixed 33.7.2-jre for checkstyle")
    }
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: floor to the fixed 3.6.1 for checkstyle")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: floor to the fixed 3.18.0 for checkstyle")
    }
}
