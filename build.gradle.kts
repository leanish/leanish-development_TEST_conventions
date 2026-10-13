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

val checkstyleSecurityFloors by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: security floor for Checkstyle's Plexus Utils dependency")
    }
    checkstyleSecurityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for Checkstyle's Guava dependency")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for annotation processors' Guava dependency")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: security floor for test annotation processors' Guava dependency")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: security floor for Checkstyle's Commons Lang dependency")
    }
}
