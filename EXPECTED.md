# Expected outcomes — convention consumer

Baseline authored 2026-10-09 against the local rc.11 checkout, including its
Unreleased plugin-source filtering/verification behavior. Locations below name
seed files; line numbers naturally move after a tool edits them.

| Case | File/line | bump-it | secure-it | Gate |
|---|---|---|---|---|
| 1: pluginManagement | `settings.gradle.kts:7` | Routine plugin-marker update 0.3.1 -> highest eligible 0.x; separate major if an eligible >=1 line exists. Edit the settings declaration, not invented consumer dependency versions. | Old plugin/tool advisories are live; may require additional security PRs/floors. | Exact marker must resolve at target on the settings/buildscript classpath; changed injected modules still face age/advisory checks. |
| 1/5: injected libraries | `build.gradle.kts:3` (origin); `src/test/java/example/fixture/SumTest.java:6` consumes assertions | Injected org.assertj:assertj-core, org.junit.jupiter:junit-jupiter, launcher and other versioned modules absent from Gradle source evidence must be in notes, NOT moves. Assert absence across the whole plan. | Security scans still include injected modules; notes are not advisory exceptions. | PluginDriven allows only existing unnamed modules' version changes in a build with a planned plugin classpath update. Additions/removals or newly named modules must fail exact-plan verification. |

Acceptance: capture base and updated `gradle-head` inventories. Require the plugin
marker to land at the plan target and check that any changed injected versions
occur only under that same planned plugin build. Tests pass using the plugin's
injected JUnit/AssertJ without manually declaring or pinning either library.
If the selected plugin target has identical injected versions, that run proves
notes/planning but not the changed-version exemption; record the gap instead of
claiming the exemption was exercised. A deliberately unrelated Java change is
outside a routine plan, so do not attach formatting repairs to that PR.

Default-branch scan may initially FAIL due to old injected tools; capture the full
finding set. PR compare should PASS if changes add no findings and inherited ones
remain, then cooldown PASS for aged versions. Final scan passes only after all
unexcepted findings are fixed. A changed plugin dependency graph may cause an
explicit verification failure; do not suppress or reclassify it as a pass.

`VERIFY: PLUGIN` — Confirm plugin 0.3.1's marker/implementation metadata, POM upload
age >=7 days, availability, and baseline compatibility with Gradle 9.3.1/JDK 25.
The public plugin JAR/marker were present in a local cache; filesystem dates are
not publish dates. Identify an aged newer plugin whose injected JUnit/AssertJ
versions actually differ, and confirm its minimum Gradle/JDK. Preview may select
a still newer eligible target: use the selected target for acceptance.

`VERIFY: BUILD` — Run ./gradlew --no-daemon check in a normal networked session on
the selected target (including plugin formatting/checkstyle, coverage and mutation
tasks). Claude reported baseline ./gradlew check passes on daemon JDK 21 with
the main Java 25 toolchain. Local Gradle startup was blocked by the sandbox's
file-lock coordination socket; target builds remain unverified.

## Shared gate and action checks

| Case | File/line | bump-it | secure-it | Gate |
|---|---|---|---|---|
| Gate adoption | `.github/workflows/supply-chain.yml:22` | Own Action update may join routine/major according to release line; repin before trials. | Only if an advisory has a selected fix. | Daily scan, PR compare, open-PR rescan; require supply-chain and cooldown after repin. |
| 8: checkout pin | `.github/workflows/ci.yml:16` | Routine: highest eligible v4 release and matching commit/comment; separate major for highest eligible newer line. | No fixed security expectation; live advisories decide. | Changed SHA must match its full release tag comment and aged release; otherwise FAIL. |
| 8: Java setup pin | `.github/workflows/ci.yml:19` | Routine v4 refresh and separate newer-major PR when eligible; preserve JDK 25 input. | Live advisories decide. | Same SHA/tag/age checks. |

Case 9: the release-age policy is `.github/supply-chain.json:2` (`releaseAgeDays: 7`).
No deliberately young fixture exists. For aged changed targets and a complete passing
comparison, cooldown should PASS. A rule-approved young security target can make
comparison PASS but cooldown FAIL (held); bump-it must not publish a held plan.
Routine and major exact targets, advisory counts and extra security units are live
registry outcomes, not fixed to the illustrative versions below. npm identity,
source or provenance breaks can block an otherwise safe version.

Gate: pinned to the supply-chain v0.2.2 release commit `a8c3f1010bc321ab3c45603eb5600ff9dda7ff3a` (`# v0.2.2`), as the
other adopters are.

`VERIFY: ACTIONS` — Confirm each exact SHA/tag pair and published release date via
GitHub (dereference annotated tags). Baseline checkout v4.1.1 is
b4ffde65f46336ab88eb53be808477a3936bae11 (2023 release). Baseline setup-java v4.2.1 is
99b8673ff64fbf99d8d325f52d9a5bdedb8483e9 (2024 release).
Choose live eligible routine and newer-major releases using their actual published
times. A moved tag, missing release or new advisory changes the expected candidate.

| Case | File/line | bump-it | secure-it | Gate |
|---|---|---|---|---|
| 6: old wrapper | `gradle/wrapper/gradle-wrapper.properties:3` | Root wrapper 9.3.1 -> highest aged stable non-broken eligible Gradle, in the routine PR. Replace all four tool-generated files; gradlew remains executable; selected distribution/jar checksums verified. | No Gradle wrapper remediation mechanism. | Wrapper is not an external module in the gate; bump-it performs wrapper-specific verification. Judge any resulting resolved-module changes normally. |

Claude verified both baseline wrapper JARs against the official Gradle 9.3.1
checksum: b3a875ddc1f044746e1b1a55f645584505f4a10438c1afea9f15e92a7c42ec13.
Gradle 9.3.1 was built 2026-01-29 and is aged. The wrapper files were generated
directly by WrapperGenerator from the cached 9.3.1 distribution.

`VERIFY: WRAPPER` — Select and verify the live wrapper target with the tool; do
not invent a fixed latest version. When preparing adoption, optionally add the
official 9.3.1 binary distribution checksum.
A wrapper-only move is not the pluginDriven exemption; if embedded dependencies
change without a planned plugin move and verification rejects them, record a
regression/failure rather than adding dependency declarations to bypass it.

