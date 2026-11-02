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

// Helper for security floors that must hold in checkstyle. Declaring them on
// checkstyle itself would stop the plugin from providing the checkstyle tool.
val securityFloors by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations.named("checkstyle") {
    extendsFrom(securityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: uncontrolled allocation in CompactHashMap deserialization, fixed in 33.7.2")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: uncontrolled allocation in CompactHashMap deserialization, fixed in 33.7.2")
    }
    securityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: uncontrolled allocation in CompactHashMap deserialization, fixed in 33.7.2")
    }
    securityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: directory traversal in extractFile, fixed in 3.6.1")
    }
    securityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: uncontrolled recursion on long inputs, fixed in 3.18.0")
    }
}
