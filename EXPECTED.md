# Expected outcomes — convention consumer

Baseline authored 2026-10-09 against the local rc.11 checkout, including its
Unreleased plugin-source filtering/verification behavior. Locations below name
seed files; line numbers naturally move after a tool edits them.

| Case | File/line | bump-it | secure-it | Gate |
|---|---|---|---|---|
| 1: pluginManagement | `settings.gradle.kts:7` | Routine plugin-marker update 0.3.1 -> highest eligible 0.x; separate major if an eligible >=1 line exists. Edit the settings declaration, not invented consumer dependency versions. | Old plugin/tool advisories are live; may require additional security PRs/floors. | Exact marker must resolve at target on the settings/buildscript classpath; changed injected modules still face age/advisory checks. |
| 1/5: injected libraries | `build.gradle.kts:3` (origin); `src/test/java/example/fixture/SumTest.java:6` consumes assertions | Injected org.assertj:assertj-core, org.junit.jupiter:junit-jupiter, launcher and other versioned modules absent from Gradle source evidence must be in notes, NOT moves. Assert absence across the whole plan. | Security scans still include injected modules; notes are not advisory exceptions. | v0.2.2 permits only existing unnamed version changes under planned-swap proof; additions/removals currently reject. B2 can accept unnamed additions/removals under that complete proof; see the version boundary below. Newly named modules remain checked. |

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


## Run acceptance and evidence (cases 8–9 and deferred majors)

EXPECTED.md is the single source of expectations. README.md contains setup and
entry commands; VALIDATION.md and REGISTRY-EVIDENCE.json contain observed evidence.
Exact live targets, advisory counts, identities and release dates depend on the
registry snapshot. A missing or too-young eligible candidate is timing-dependent,
not proof that the fixture or tool failed. No young release is fabricated.

Run secure-it and bump-it independently against the same seeded main revision;
capture evidence before merging either tool's PRs. Do not merge parent updates
before testing transitive security fixes. Preserve the seed revision externally
for repeat trials; after merges, do not expect the same candidates/findings.
Unchanged weeks may review already-open PRs rather than create new ones.

bump-it's default maximum is **three new major PRs per run**, in addition to the
routine unit. An eligible major can be deferred behind higher-priority packages,
including Actions and the new glob direct dependency. An absent wordwrap,
annotations, setup-java or other major PR is not a bug solely because it is absent.
Check the deployed limit and each unit's status/priority; distinguish deferred,
already-open, held, failed, no eligible candidate and notes-only dependencies.
Capture later runs (or a deliberately configured larger limit) to exercise every
eligible major. Never mark a deferred unit as a successful update.

For every run, retain the tool version/commit, configuration, baseline/head IDs,
complete tool record **including units, waiting, gaps, notes and errors**, candidate
versions, plan payloads, PR title/body/diff, and verification/build logs. Include
failed units' attempted plans and diffs even when no PR exists. If the runner does
not retain a failed plan/diff, report that evidence gap rather than inventing a
root cause. Preserve inventories for both sides, resolved and declared versions,
findings fixed/inherited/added, the full compare report, cooldown verdict/held
versions and CI results. Store scratch evidence in ignored .validation/ or outside
the repository; no generated diagnostics belong in a remediation PR.

Default-branch scan must fail on the seeded unexcepted advisories. A successful
fix PR can pass compare with other findings inherited; this does not mean main
is clean. CLI exit zero, creation of a PR, or a passing smoke test alone is not
acceptance. Judge every changed resolved artifact for advisories, age and identity.
Missing registry data/inventory coverage is incomplete, never a clean pass.

`VERIFY: RUN-EVIDENCE` — Confirm the deployed maxNewMajorsPerRun value and retain
all fields and failed-unit artifacts above in the next weekly report. Reconcile
all cases against units, not only the published PR list.

## v0.2.2 versus the proposed v0.2.3 fixes

The gate remains pinned to v0.2.2. These are version-specific expectations for
B1/B2/B3 from the run-1 review, not permission to weaken verification:

| Fix | v0.2.2 behavior | With the reviewed fix in v0.2.3 |
|---|---|---|
| B1: capture declarations after resolution | Lazy/default dependencies can appear only on one side of inventory, e.g. Checkstyle at :checkstyle. Apparent declaration additions are not evidence of an actual fixture edit. | Two-phase export resolves configurations before recording declarations, so unchanged default dependencies are recorded consistently. Any unresolved configuration still fails closed. |
| B2: plugin graph additions/removals | bump-it's planned-swap proof exempts only existing unnamed modules' version changes. Kotlin buildSrc graph removals/additions and Lombok newly present at :testCompileClasspath can reject otherwise mechanical plugin bumps. | Additions/removals of unnamed plugin-driven dependencies can be accepted ONLY in the same build with a planned classpath plugin move, when the whole edit (including file modes) proves nothing but planned swaps and independently verified npm/Action/wrapper bytes changed. Planned declarations must still land exactly. Every added/changed resolved artifact still faces the gate. |
| B3: secure-it shares the plugin proof | Security verification does not apply bump-it's equivalent plugin-driven proof. Kotlin buildSrc version changes may reject a security plan that includes a plugin move. | Such changes can be accepted under the same complete planned-swap proof when a matching plugin classpath move is actually planned. Security floor insertions are not version swaps: a mixed floor/plugin edit needs its own exact proof; do not assume B3 permits it. |

Ignoring **/.kotlin/ is the fixture repair, not a tool-wide exemption for extra
edits. No Kotlin error log is present in these local baselines. Previously opened
PRs containing tracked logs still need those files removed in their own heads;
adding this ignore rule alone does not remove tracked files. Arbitrary source or
policy edits, unexplained new/removed files, newly named dependencies, other-build
changes and missing evidence must continue to fail the plugin-driven proof.

B1 can remove false inventory deltas; B2 is relevant to bump-it's conventions
major and Gradle routine; B3 is conditionally relevant to security plugin plans.
Without the failed security plan/diff, do not attribute the observed Checkstyle
addition entirely to B1 or promise B3 alone makes a floor-bearing PR pass.
These repairs do not change case-10 declaration versus resolution semantics.
The independent buildSrc Commons IO convention declaration is a normal project
dependency, not a classpath plugin trigger; unrelated induced changes still use
the documented limitation in packages/ci/README.md's Gradle declarations section.

`VERIFY: SUPPLY-CHAIN-0.2.3` — Check the shipped implementation and changelog for
B1/B2/B3, then rerun tool verification and gate CI with recorded versions. Do not
claim v0.2.3 behavior from the still-pinned v0.2.2 workflow. Retain failures caused
by age, advisories, unsupported edits or insufficient proof as failures.
