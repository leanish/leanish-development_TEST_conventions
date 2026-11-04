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

// The checkstyle configuration is filled with the plugin's default tool, so the floors go in a helper it extends.
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava CompactHashMap/CompactHashSet/MapMakerInternalMap deserialization allocates memory without bound; 33.7.2 is the first patched version")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava CompactHashMap/CompactHashSet/MapMakerInternalMap deserialization allocates memory without bound; 33.7.2 is the first patched version")
    }
    checkstyleSecurityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava CompactHashMap/CompactHashSet/MapMakerInternalMap deserialization allocates memory without bound; 33.7.2 is the first patched version")
    }
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils extractFile directory traversal; 3.6.1 is the first patched 3.x version")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 uncontrolled recursion on long inputs; 3.18.0 is the first patched version")
    }
}
