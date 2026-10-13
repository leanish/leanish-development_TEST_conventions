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

configurations.getByName("checkstyle").extendsFrom(checkstyleFloors)

dependencies {
    add(checkstyleFloors.name, "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: security floor for transitive checkstyle dependency")
    }
    add(checkstyleFloors.name, "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for transitive checkstyle dependency")
    }
    add(checkstyleFloors.name, "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: security floor for transitive checkstyle dependency")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for transitive annotation processor dependency")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for transitive test annotation processor dependency")
    }
}
