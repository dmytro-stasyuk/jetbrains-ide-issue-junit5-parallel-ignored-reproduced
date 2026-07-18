# junit5-parallel-ignored-repro

Clean-room reproduction of passing JUnit 5 tests being shown as **"ignored"** in the
IntelliJ Run tool window under **parallel execution**.

> **Reported to JetBrains:** [IDEA-391751](https://youtrack.jetbrains.com/issue/IDEA-391751/JUnit-5-parallel-execution-passing-tests-shown-as-ignored-with-duplicated-test-events-in-the-IDE-test-runner)
> (IntelliJ IDEA, subsystem *Java. Tests. JUnit*).

## What this proves

This project contains **only 40 trivial, always-passing test classes** — plain
`@Nested` + `@Test` methods whose bodies are `Thread.sleep(2)`. There is:

- no custom test infrastructure, base classes, shared state, or fixtures,
- no `@Disabled`, no `Assumptions`, no custom extensions/listeners,
- no third-party dependencies beyond JUnit 5.

The only thing configured is a `junit-platform.properties` enabling class-level parallelism.

If passing tests still show as "ignored" here, the cause is neither the test code nor any
plugin — it is a thread-safety bug in IntelliJ's in-IDE JUnit 5 runner
(`com.intellij.junit5.JUnit5TestExecutionListener`, in `plugins/junit/lib/junit5-rt.jar`).

## Reproduce in IntelliJ

1. Open this folder as a Maven project in IntelliJ IDEA.
2. Make sure tests run with the **IntelliJ JUnit runner**, not delegated to Maven:
   the run config must be a JUnit one (the `◁▷` icon), created by right-clicking a test.
   (Running the Maven `test` goal will *not* show the bug — Surefire uses its own reporter.)
3. Right-click `src/test/java/repro` → **Run 'Tests in repro'**. This runs all 800 tests.
4. Re-run it several times.

### Expected
1600 events for 800 tests: every test green, **0 ignored**.

### Actual
Dozens of green tests show the "ignored" (circle-slash) icon — each carrying a real
duration (they ran). The affected set is **different on every run** (it's a race), and
whole `@Nested` classes tend to flip together. Total runtime is a few seconds.

## Reproduce headlessly (no IDE, drives the same listener directly)

`target/test-classes` must be compiled and `junit-platform.properties` present at its root.

```
javac -cp <jupiter+platform jars> -d target/test-classes $(find src/test/java -name '*.java')
cp src/test/resources/junit-platform.properties target/test-classes/

java -cp "target/test-classes:<junit5-rt.jar>:<idea_rt.jar>:<jupiter+platform jars>" \
     Driver target/test-classes | grep -c testIgnored
```

where `Driver` (see `headless/Driver.java`) attaches
`com.intellij.junit5.JUnit5TestExecutionListener` and runs `selectClasspathRoots`.
Observed: 150–250 spurious `testIgnored` per run here; **0** when
`junit.jupiter.execution.parallel.enabled=false`.

## Proof the fault is the IDE listener, not JUnit

Attaching JUnit's own `SummaryGeneratingListener` and IntelliJ's listener to the *same* run:

```
JUnit's OWN result (org.junit SummaryGeneratingListener):
   testsStarted=800  succeeded=800  skipped=0  failed=0
   raw executionSkipped(test) calls from the engine = 0
IntelliJ's listener (com.intellij.junit5) on the SAME run:
   ##teamcity[testIgnored] emitted = 480
```

JUnit executed 800/800 successfully and fired zero `executionSkipped` events; IntelliJ's
listener invented 480 `testIgnored` on the same run. Upgrading JUnit (5.11.4 → 5.14.1)
does not change this.

## Fix / workaround

- The bug is in the IntelliJ platform listener — report to JetBrains YouTrack.
- Maven/Gradle/CI runs are unaffected (different reporter). Parallel is safe there.
- For a trustworthy tree from the IDE, run with `parallel.enabled=false`, or treat the
  IDE "ignored" as cosmetic and trust the pass count / the test framework's own report.
