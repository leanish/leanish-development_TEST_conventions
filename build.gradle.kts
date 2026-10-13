plugins {
    `java-library`
    id("io.github.leanish.java-conventions")
}

group = "io.github.leanish.tests"
version = "1.0.0"
description = "Synthetic convention consumer for supply-chain end-to-end runs."

val checkstyleSecurityFloors by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    add(checkstyleSecurityFloors.name, "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: Checkstyle pulls an affected Plexus Utils")
    }
    add(checkstyleSecurityFloors.name, "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Checkstyle pulls an affected Guava")
    }
    add(checkstyleSecurityFloors.name, "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: Checkstyle pulls an affected Commons Lang")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Annotation processors pull an affected Guava")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Test annotation processors pull an affected Guava")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}
