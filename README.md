### Fastest zero abstraction number conversion android native app

![CI](https://github.com/simonvar/Numer/workflows/CI/badge.svg)
![Release](https://github.com/simonvar/Numer/workflows/Build%20and%20Release%20APK/badge.svg)

## CI/CD Pipeline

This project includes automated GitHub Actions workflows for continuous integration and deployment:

### Workflows

1. **CI Workflow** (`.github/workflows/ci.yml`)
   - Triggers on push/PR to `master`, `main`, or `develop` branches
   - Runs tests and builds debug APK
   - Uploads debug APK as artifact for verification

2. **Release Workflow** (`.github/workflows/release.yml`)
   - Triggers on:
     - Push to `release/*` branches
     - Git tags starting with `v*` (e.g., `v2.0`, `v2.1`)
     - Manual GitHub releases
   - Builds release APK with optimizations enabled
   - Automatically creates GitHub releases for version tags
   - Uploads APK to GitHub releases page

### Creating a Release

To create a new release:

1. **Using Git Tags**: Push a tag starting with `v`
   ```bash
   git tag v2.1
   git push origin v2.1
   ```

2. **Using Release Branches**: Push to a release branch
   ```bash
   git checkout -b release/v2.1
   git push origin release/v2.1
   ```

3. **Manual Release**: Create a release manually in GitHub UI

The workflow will automatically build and attach the APK to the release.