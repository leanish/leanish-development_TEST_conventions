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

// Helper for the security floors on checkstyle's tool classpath. checkstyle extends it instead of
// declaring the floors on it, so the plugin's default checkstyle tool is kept.
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.7.1 and earlier allow uncontrolled memory allocation when deserializing CompactHashMap, CompactHashSet and MapMakerInternalMap")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.7.1 and earlier allow uncontrolled memory allocation when deserializing CompactHashMap, CompactHashSet and MapMakerInternalMap")
    }
    checkstyleSecurityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.7.1 and earlier allow uncontrolled memory allocation when deserializing CompactHashMap, CompactHashSet and MapMakerInternalMap")
    }
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils before 3.6.1 has a directory traversal in extractFile")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 before 3.18.0 has uncontrolled recursion when processing long inputs")
    }
}
