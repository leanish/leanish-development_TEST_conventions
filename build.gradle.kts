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

// Security floors. The checkstyle plugin fills its `checkstyle` configuration with defaults only while it has
// no dependencies of its own, so its floors live in a helper configuration that `checkstyle` extends.
val dependencyFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(dependencyFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor the annotation processor path at the fixed guava release")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor the test annotation processor path at the fixed guava release")
    }
    dependencyFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor the checkstyle classpath at the fixed guava release")
    }
    dependencyFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: floor the checkstyle classpath at the fixed plexus-utils release")
    }
    dependencyFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: floor the checkstyle classpath at the fixed commons-lang3 release")
    }
}
