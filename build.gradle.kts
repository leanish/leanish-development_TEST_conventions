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

val guavaSecurityFloor by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}
val checkstyleSecurityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("annotationProcessor") {
    extendsFrom(guavaSecurityFloor)
}
configurations.named("testAnnotationProcessor") {
    extendsFrom(guavaSecurityFloor)
}
configurations.named("checkstyle") {
    extendsFrom(guavaSecurityFloor, checkstyleSecurityFloors)
}

dependencies {
    add(guavaSecurityFloor.name, "com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: enforce the fixed Guava version for processors and Checkstyle")
    }
    add(checkstyleSecurityFloors.name, "org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: enforce the fixed Plexus Utils version for Checkstyle")
    }
    add(checkstyleSecurityFloors.name, "org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: enforce the fixed Commons Lang version for Checkstyle")
    }
}
