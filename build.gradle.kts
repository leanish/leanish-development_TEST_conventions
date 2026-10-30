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

val checkstyleSecurityFloors = configurations.create("checkstyleSecurityFloors") {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Avoid vulnerable Guava versions")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Avoid vulnerable Guava versions")
    }
    add(checkstyleSecurityFloors.name, "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Avoid vulnerable Guava versions")
    }
    add(checkstyleSecurityFloors.name, "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: Avoid vulnerable plexus-utils versions")
    }
    add(checkstyleSecurityFloors.name, "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: Avoid vulnerable commons-lang3 versions")
    }
}
