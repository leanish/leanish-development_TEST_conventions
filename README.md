# Convention consumer fixture

Synthetic consumer covering cases 1, 5, 6, 8 and 9: settings pluginManagement,
plugin-provided test and quality libraries, a Gradle wrapper and pinned Actions.

```sh
./gradlew --no-daemon check
```

Use a Gradle daemon on JDK 21 (the tool runner's default), with JDK 25 installed
for the main build toolchain. CI installs JDK 25. The tests use the convention
plugin's libraries without additional consumer declarations.

Case expectations, acceptance steps and validation evidence are kept outside the
fixture repositories for human and release validation. These repositories are test inputs;
do not publish packages. Claude manages GitHub adoption and the run configuration.
This fixture isn't part of regular tool passes: it runs only in release-candidate
passes that opt fixtures in (`SUPPLY_CHAIN_FIXTURES=1`).
The gate's pin is the `uses:` line in `.github/workflows/supply-chain.yml` (its
history is in git); `fixture-repin` moves it after each stable release. The gate uses
the same commit for inventory and comparison. Tools are deployed separately; record
their release/commit for each trial.
