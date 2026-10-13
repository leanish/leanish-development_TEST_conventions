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
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleSecurityFloors)
}

dependencies {
    add("checkstyleSecurityFloors", "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: fix the vulnerable Checkstyle dependency")
    }
    add("annotationProcessor", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: fix the vulnerable annotation processor dependency")
    }
    add("checkstyleSecurityFloors", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: fix the vulnerable Checkstyle dependency")
    }
    add("testAnnotationProcessor", "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: fix the vulnerable test annotation processor dependency")
    }
    add("checkstyleSecurityFloors", "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: fix the vulnerable Checkstyle dependency")
    }
}
