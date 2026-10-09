# Convention consumer fixture

Covers cases **1, 5, 6, 8, 9**: settings pluginManagement, injected dependencies in
notes, plugin-driven version changes during verification, an old wrapper, aged
Actions pins, and timing expectations. One tiny Java class and two tests exercise
the plugin's injected JUnit/AssertJ dependencies and quality tools.

```sh
./gradlew --no-daemon check
```

Install JDK 25 locally; CI and the gate do so explicitly. No explicit test-library
versions are declared here. The source index does not read ordinary Java test
imports, so they must not turn the injected modules into bump candidates. Do not
add their coordinates to Gradle script comments or catalogs. A plugin update on
the settings classpath may change existing unnamed injected dependencies; it may
not silently add/remove them or bypass the gate. An extra dependency or a newly
named dependency remains subject to exact-plan verification.

The plugin baseline is 0.3.1, found in a local public artifact cache; age and the
newer eligible plugin release need registry confirmation. secure-it may also
repair vulnerabilities in the old plugin's resolved tools; absence of such work
is not a fixed assertion. The acceptance target here is a successful bump plan
that moves the plugin while keeping injected-library candidates out of the plan.

These repositories are synthetic test inputs, not packages to publish. The baseline
contains deliberately old dependencies. The security fixtures should make `scan`
red while their build/tests stay green. On PRs, `compare` permits inherited findings
and fails on newly introduced findings; a green PR does not prove `main` is clean.

Claude owns GitHub creation/adoption and the weekly tool configuration. Use private
repos under `leanish`, default branch `main`, with no Dependabot update PRs. Require
`supply-chain / supply-chain`, `supply-chain / cooldown`, and the build/test check
when the account plan supports required checks on private repositories. Keep the
daily gate scan enabled; the macOS weekly tools are separate from this workflow.

The gate is pinned to the supply-chain v0.2.2 release commit
`a8c3f1010bc321ab3c45603eb5600ff9dda7ff3a` (`v0.2.2`), like the other adopters.

Before the first live run, resolve every `VERIFY:` in EXPECTED.md, capture a gate
inventory/report, then preview candidates as the adoption guide describes. Run
secure-it first on the vulnerable baseline, capture its plan/diff/report without
merging, then bump-it against that same baseline. Each tool plans from `main`, not
from the other's PR. Record exact versions chosen, findings fixed/inherited,
notes, failures, cooldown and CI results; CLI exit 0 alone is not acceptance.
Merge/rebase only after collecting that evidence, then run review and scan again.

Repeated weeks on an unchanged baseline should produce `already-open` and review
existing PRs. After merging fixes, those moves disappear; a new PR every week is
not expected. Retain the initial baseline revision externally for repeat trials.
If re-seeding, use a deliberate ordinary commit or a fresh test repo; the gate
should reject reintroducing vulnerabilities. Do not force-push or weaken policy.

No release is fabricated to test age. The expected baseline fixes are historically
aged; newest routine/major targets depend on registry time and the current advisory
snapshot. bump-it must select only eligible aged targets. If a future security fix
is younger than 7 days, expect a draft security-cooldown PR, `supply-chain` passing
only after its rule proof, and `cooldown` red until it ages. Missing registry data
is an incomplete run, never a clean verdict. Exact historical target examples in
EXPECTED.md are not frozen assertions about the live registry.
