## 1. Scaffold workflow file

- [x] 1.1 Create the directory `.github/workflows/` at the repository root if it does not already exist
- [x] 1.2 Create the file `.github/workflows/ci.yml` with the workflow name `CI – E2E Serenity SauceDemo`

## 2. Configure workflow triggers

- [x] 2.1 Add `on.push.branches: [main]` so the workflow fires on every direct push to `main`
- [x] 2.2 Add `on.pull_request.branches: [main]` so the workflow fires on PRs targeting `main`

## 3. Define the e2e-tests job

- [x] 3.1 Declare a job named `e2e-tests` with `runs-on: ubuntu-latest`

## 4. Add checkout step

- [x] 4.1 Add a step using `actions/checkout@v4` as the first job step to clone the repository

## 5. Set up Java 21 with Maven cache

- [x] 5.1 Add a step using `actions/setup-java@v4` with `java-version: '21'`, `distribution: 'temurin'`, and `cache: 'maven'`

## 6. Ensure Maven Wrapper is executable

- [x] 6.1 Add a step that runs `chmod +x mvnw` before invoking the wrapper

## 7. Execute E2E tests

- [x] 7.1 Add a step that runs `./mvnw verify` to execute all Serenity/Cucumber scenarios via `maven-failsafe-plugin`

## 8. Upload Serenity report artifact

- [x] 8.1 Add a step using `actions/upload-artifact@v4` with `if: always()`, `name: serenity-report`, and `path: target/site/serenity/`

## 9. Verify pipeline end-to-end

- [x] 9.1 Push the branch to GitHub and confirm the workflow run is created and the `e2e-tests` job is queued
- [x] 9.2 Confirm the job completes on `ubuntu-latest` with Java 21 and all scenarios pass
- [x] 9.3 Confirm the `serenity-report` artifact is downloadable from the Actions run summary
- [x] 9.4 Merge to `main` once the pipeline is green
