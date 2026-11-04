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

// Declaring the floors on `checkstyle` itself would drop the Checkstyle tool the plugin supplies by default,
// so they live in a helper configuration that `checkstyle` extends.
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre was transitive on the annotation processor path; floored to the fixed version")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre was transitive on the test annotation processor path; floored to the fixed version")
    }
    checkstyleSecurityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre was transitive on the checkstyle path; floored to the fixed version")
    }
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils 3.3.0 was transitive on the checkstyle path; floored to the fixed version")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 3.8.1 was transitive on the checkstyle path; floored to the fixed version")
    }
}
