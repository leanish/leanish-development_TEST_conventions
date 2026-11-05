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

// Helper configuration carrying security floors for checkstyle. checkstyle extends it instead of
// receiving the floors directly, so the plugin's default checkstyle tool is not dropped.
val securityFloors by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = false
}

configurations.named("checkstyle") {
    extendsFrom(securityFloors)
}

dependencies {
    annotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor guava 33.7.2-jre for the annotation processor classpath")
    }
    testAnnotationProcessor("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor guava 33.7.2-jre for the test annotation processor classpath")
    }
    securityFloors("com.google.guava:guava:33.7.2-jre") {
        because("GHSA-xxph-c9ww-hj94: floor guava 33.7.2-jre for checkstyle")
    }
    securityFloors("org.codehaus.plexus:plexus-utils:3.6.1") {
        because("GHSA-6fmv-xxpf-w3cw: floor plexus-utils 3.6.1 for checkstyle")
    }
    securityFloors("org.apache.commons:commons-lang3:3.18.0") {
        because("GHSA-j288-q9x7-2f5v: floor commons-lang3 3.18.0 for checkstyle")
    }
}
