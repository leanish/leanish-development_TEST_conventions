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

// checkstyle gets its default tool only while it declares no dependencies, so the floors go through a helper it extends
val checkstyleFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(checkstyleFloors)
}

dependencies {
    checkstyleFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: plexus-utils below 3.6.1 allows directory traversal in extractFile")
    }
    checkstyleFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: commons-lang3 below 3.18.0 recurses without bound on long inputs")
    }
}
