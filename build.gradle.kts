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
    checkstyleSecurityFloors("org.codehaus.plexus:plexus-utils") {
        version {
            require("3.6.1")
        }
        because("GHSA-6fmv-xxpf-w3cw: checkstyle resolves a plexus-utils version with a known advisory")
    }
    checkstyleSecurityFloors("com.google.guava:guava") {
        version {
            require("33.7.2-jre")
        }
        because("GHSA-xxph-c9ww-hj94: checkstyle resolves a guava version with a known advisory")
    }
    checkstyleSecurityFloors("org.apache.commons:commons-lang3") {
        version {
            require("3.18.0")
        }
        because("GHSA-j288-q9x7-2f5v: checkstyle resolves a commons-lang3 version with a known advisory")
    }
    annotationProcessor("com.google.guava:guava") {
        version {
            require("33.7.2-jre")
        }
        because("GHSA-xxph-c9ww-hj94: the annotation processor path resolves a guava version with a known advisory")
    }
    testAnnotationProcessor("com.google.guava:guava") {
        version {
            require("33.7.2-jre")
        }
        because("GHSA-xxph-c9ww-hj94: the test annotation processor path resolves a guava version with a known advisory")
    }
}
