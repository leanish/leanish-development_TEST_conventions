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
