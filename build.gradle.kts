plugins {
    `java-library`
    id("io.github.leanish.java-conventions")
}

group = "io.github.leanish.tests"
version = "1.0.0"
description = "Synthetic convention consumer for supply-chain weekly runs."

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Security floor for annotation processor dependencies")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: Security floor for annotation processor dependencies")
    }
}

configurations.named("checkstyle") {
    // Keep Gradle's default Checkstyle dependency before adding security floors.
    withDependencies {
        add(project.dependencies.create("org.codehaus.plexus:plexus-utils:3.6.1").apply {
            because("GHSA-6fmv-xxpf-w3cw: Security floor for Checkstyle dependencies")
        })
        add(project.dependencies.create("com.google.guava:guava:33.7.2-jre").apply {
            because("GHSA-xxph-c9ww-hj94: Security floor for Checkstyle dependencies")
        })
        add(project.dependencies.create("org.apache.commons:commons-lang3:3.18.0").apply {
            because("GHSA-j288-q9x7-2f5v: Security floor for Checkstyle dependencies")
        })
    }
}
