## Why

The project has no automated CI pipeline, so E2E tests are only executed manually on developer machines. Adding a GitHub Actions workflow ensures regressions are caught automatically on every push and pull request, providing continuous validation of the full purchase flow on SauceDemo.

## What Changes

- Add `.github/workflows/ci.yml`: GitHub Actions workflow that runs all E2E tests headlessly using `./mvnw verify`.
- Upload the Serenity HTML report (`target/site/serenity/`) as a workflow artifact so test results are accessible from the GitHub Actions run summary.

## Capabilities

### New Capabilities

- `ci-pipeline`: GitHub Actions workflow that triggers on `push` and `pull_request` to the main branch, sets up Java 21, runs `./mvnw verify` in headless mode, and publishes the Serenity report as a downloadable artifact.

### Modified Capabilities

## Impact

- New directory: `.github/workflows/`
- New file: `.github/workflows/ci.yml`
- No changes to existing source code or `pom.xml`
- Relies on Selenium Manager's automatic ChromeDriver management (already in place) and headless Chrome available on `ubuntu-latest` GitHub-hosted runners
