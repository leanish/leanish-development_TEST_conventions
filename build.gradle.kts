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

// Checkstyle extends this helper: declaring the floors on checkstyle itself
// would stop the plugin from supplying the checkstyle tool.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils below the fixed version pulled in by checkstyle's doxia dependencies")
    }
    checkstyleFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below the fixed version pulled in by checkstyle")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 below the fixed version pulled in by checkstyle's doxia dependencies")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below the fixed version on the annotation processor path")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava below the fixed version on the test annotation processor path")
    }
}
