# Convention consumer fixture

Synthetic consumer covering cases 1, 5, 6, 8 and 9: settings pluginManagement,
plugin-provided test and quality libraries, a Gradle wrapper and pinned Actions.

```sh
./gradlew --no-daemon check
```

Use a Gradle daemon on JDK 21 (the weekly machine's default), with JDK 25 installed
for the main build toolchain. CI installs JDK 25. The tests use the convention
plugin's libraries without additional consumer declarations.

[EXPECTED.md](EXPECTED.md) is the single source of case expectations, runnable
acceptance steps, timing caveats and VERIFY items. [VALIDATION.md](VALIDATION.md)
records checks already performed. These private repositories are test inputs;
do not publish packages. Claude manages GitHub adoption and weekly configuration.
Gate adoption uses the same supply-chain commit for inventory and comparison;
see EXPECTED.md for the prerelease pin and released v0.3.0 acceptance.
