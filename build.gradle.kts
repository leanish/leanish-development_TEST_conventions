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

// Security floors for transitive dependencies. checkstyle keeps its default tool, so the floors
// live in a helper configuration that it extends.
val securityFloors by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations.named("checkstyle") {
    extendsFrom(securityFloors)
}

dependencies {
    securityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre to 33.7.2-jre, checkstyle classpath")
    }
    securityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils 3.3.0 to 3.6.1, checkstyle classpath")
    }
    securityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 3.8.1 to 3.18.0, checkstyle classpath")
    }

    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre to 33.7.2-jre, annotation processor classpath")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre to 33.7.2-jre, test annotation processor classpath")
    }
}
