## ADDED Requirements

### Requirement: Workflow triggers on push and pull_request to main branch
The CI workflow SHALL be triggered automatically on `push` events and `pull_request` events targeting the `main` branch, ensuring every code change against the primary branch is validated.

#### Scenario: Workflow starts on push to main
- **WHEN** a commit is pushed directly to the `main` branch
- **THEN** the GitHub Actions workflow run is created and the `e2e-tests` job is queued

#### Scenario: Workflow starts on pull request targeting main
- **WHEN** a pull request is opened, synchronized, or reopened against the `main` branch
- **THEN** the GitHub Actions workflow run is created and the `e2e-tests` job is queued

### Requirement: Job runs on ubuntu-latest runner
The `e2e-tests` job SHALL execute on a GitHub-hosted `ubuntu-latest` runner, which provides a pre-installed Google Chrome binary required by Selenium.

#### Scenario: Job is assigned to ubuntu-latest
- **WHEN** the workflow is triggered
- **THEN** the job is executed on an `ubuntu-latest` GitHub-hosted runner

### Requirement: Java 21 is configured with Maven dependency cache
The workflow SHALL use `actions/setup-java@v4` to install Java 21 (Temurin distribution) and enable the built-in `maven` cache, so that `~/.m2/repository` dependencies are restored from cache on subsequent runs.

#### Scenario: Java 21 is available before build step
- **WHEN** the `setup-java` step completes
- **THEN** `java -version` reports Java 21 and `JAVA_HOME` is set appropriately

#### Scenario: Maven cache is restored on a repeated run
- **WHEN** the workflow runs a second time after dependencies were downloaded
- **THEN** the Maven cache is restored and dependency download time is reduced

### Requirement: Maven Wrapper is made executable before test execution
The workflow SHALL execute `chmod +x mvnw` before invoking `./mvnw`, ensuring the wrapper script is executable regardless of any permission loss during checkout.

#### Scenario: mvnw permission is set
- **WHEN** the chmod step runs
- **THEN** `./mvnw` has execute permissions and can be invoked without a permission error

### Requirement: E2E tests are executed via ./mvnw verify in headless mode
The workflow SHALL run `./mvnw verify` to execute all E2E tests through the `maven-failsafe-plugin`. The headless Chrome configuration SHALL be sourced entirely from `serenity.conf`, with no additional flags passed in the workflow.

#### Scenario: Tests complete successfully
- **WHEN** `./mvnw verify` is executed on the runner
- **THEN** all Cucumber/Serenity scenarios run headlessly and the build exits with code `0`

#### Scenario: Build fails when a test fails
- **WHEN** one or more E2E scenarios fail during `./mvnw verify`
- **THEN** the Maven build exits with a non-zero code and the workflow job is marked as failed

### Requirement: Serenity report is uploaded as a workflow artifact unconditionally
The workflow SHALL upload the directory `target/site/serenity/` as a named workflow artifact after the test step, using `if: always()` so the report is accessible from the GitHub Actions run summary regardless of whether tests passed or failed.

#### Scenario: Report artifact is available after a successful run
- **WHEN** `./mvnw verify` exits with code `0`
- **THEN** a downloadable artifact named `serenity-report` containing the HTML report is visible in the run summary

#### Scenario: Report artifact is available after a failed run
- **WHEN** `./mvnw verify` exits with a non-zero code
- **THEN** a downloadable artifact named `serenity-report` containing the HTML report is still visible in the run summary
