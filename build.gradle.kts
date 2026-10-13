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

// Helper configuration the checkstyle tool extends: declaring on checkstyle itself would drop the plugin's default tool.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    add("checkstyleFloors", "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: floor keeps the checkstyle tool off vulnerable plexus-utils 3.3.0")
    }
    add("checkstyleFloors", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor keeps the checkstyle tool off vulnerable guava 33.5.0-jre")
    }
    add("checkstyleFloors", "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: floor keeps the checkstyle tool off vulnerable commons-lang3 3.8.1")
    }
    add("annotationProcessor", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor keeps the annotation processor path off vulnerable guava 33.5.0-jre")
    }
    add("testAnnotationProcessor", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor keeps the test annotation processor path off vulnerable guava 33.5.0-jre")
    }
}
