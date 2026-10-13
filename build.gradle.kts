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

// Security floors for transitive dependencies of the checkstyle tool and the annotation processors.
// The checkstyle configuration is declared through a helper it extends, so the plugin keeps adding its default tool.
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils 3.3.0 is affected; 3.6.1 fixes it")
    }
    checkstyleFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is affected; 33.7.2-jre fixes it")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 3.8.1 is affected; 3.18.0 fixes it")
    }
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is affected; 33.7.2-jre fixes it")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: guava 33.5.0-jre is affected; 33.7.2-jre fixes it")
    }
}
