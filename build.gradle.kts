plugins {
    `java-library`
    id("io.github.leanish.java-conventions")
}

val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

group = "io.github.leanish.tests"
version = "1.0.0"
description = "Synthetic convention consumer for supply-chain end-to-end runs."

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    add(checkstyleSecurityFloors.name, "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: Fixes the vulnerable Plexus Utils version on the Checkstyle classpath.")
    }
    add(checkstyleSecurityFloors.name, "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Fixes the vulnerable Guava version on the Checkstyle classpath.")
    }
    add(checkstyleSecurityFloors.name, "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: Fixes the vulnerable Commons Lang version on the Checkstyle classpath.")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Fixes the vulnerable Guava version for annotation processing.")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Fixes the vulnerable Guava version for test annotation processing.")
    }
}
