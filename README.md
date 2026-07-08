# Your Daily Improvement

An Android application scaffold built with **Kotlin** and **Jetpack Compose**.

> Milestone 0 — Project setup. This is the runnable base structure the next
> architecture milestone will build upon.

## Tech stack

| Area            | Choice                                   |
| --------------- | ---------------------------------------- |
| Language        | Kotlin 2.0                               |
| UI toolkit      | Jetpack Compose (Material 3)             |
| Architecture    | Layered (domain / data / ui) + MVVM      |
| DI              | Hilt (KSP)                               |
| Navigation      | Navigation Compose                       |
| Async           | Kotlin Coroutines + Flow                 |
| Build system    | Gradle (Kotlin DSL) + version catalog    |
| Android Gradle Plugin | 8.7.3                              |
| compileSdk / targetSdk | 35 (Android 15)                   |
| minSdk          | 24 (Android 7.0)                         |
| Package name    | `com.yourdailyimprovement.androidapp`    |

## Project structure

```
android app/
├── app/
│   ├── build.gradle.kts                # App module build config
│   ├── proguard-rules.pro
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/yourdailyimprovement/androidapp/
│       │   │   ├── YourDailyImprovementApp.kt   # @HiltAndroidApp Application
│       │   │   ├── MainActivity.kt              # Single-activity host
│       │   │   ├── di/                          # Hilt modules
│       │   │   ├── domain/                      # Business logic (framework-free)
│       │   │   │   ├── model/                   #   Domain models
│       │   │   │   ├── repository/              #   Repository interfaces
│       │   │   │   └── usecase/                 #   Use cases
│       │   │   ├── data/                        # Data layer
│       │   │   │   └── repository/              #   Repository implementations
│       │   │   └── ui/                          # Presentation layer
│       │   │       ├── home/                    #   HomeScreen + ViewModel + UiState
│       │   │       ├── navigation/              #   NavHost + routes
│       │   │       └── theme/                   #   Compose theme
│       │   └── res/                    # Resources (strings, icons, themes)
│       ├── test/                       # Local JVM unit tests
│       └── androidTest/                # Instrumented tests
├── build.gradle.kts                    # Root build file
├── settings.gradle.kts                 # Module + repository setup
├── gradle/
│   ├── libs.versions.toml              # Dependency version catalog
│   └── wrapper/                        # Gradle wrapper
├── gradlew / gradlew.bat               # Wrapper launch scripts
└── README.md
```

## Prerequisites

This machine does **not** currently have the Android toolchain installed. To
build and run the app you need:

- **JDK 17** (bundled with recent Android Studio as the JBR).
- **Android SDK** with platform **API 35** and build-tools.
- Optionally **Android Studio** (Ladybug or newer) — the simplest way to get
  the SDK, an emulator, and a JDK in one install.

If you use the command line instead of Android Studio, create a
`local.properties` file in the project root pointing at your SDK:

```properties
sdk.dir=C\:\\Users\\aspin\\AppData\\Local\\Android\\Sdk
```

(Android Studio writes this file for you automatically.)

## Build & run

### With Android Studio
1. Open the `android app` folder as a project.
2. Let Gradle sync (it downloads Gradle 8.11.1 and dependencies on first run).
3. Pick an emulator (or a connected device) and press **Run ▶**.

### From the command line
```bash
# Build a debug APK
./gradlew assembleDebug        # gradlew.bat on Windows

# Install & launch on a running emulator/device
./gradlew installDebug

# Run unit tests
./gradlew test
```

## Architecture

The app follows a layered, unidirectional-dependency design:

```
ui (Compose + ViewModel)  ──►  domain (use cases, models, repo interfaces)  ◄──  data (repo impls)
```

- **UI** observes immutable `UiState` from a `ViewModel` via `StateFlow`; it
  never touches the data layer directly.
- **Domain** is pure Kotlin (no Android imports) — easy to unit test.
- **Data** implements the domain's repository interfaces. The concrete binding
  lives in a single Hilt module (`di/RepositoryModule.kt`), so swapping the
  in-memory source for a database or API is a one-file change.

The included vertical slice — a daily improvement tip — runs the full path:
`ImprovementRepositoryImpl → GetDailyTipUseCase → HomeViewModel → HomeScreen`.

## What you get

Launching the app shows a Compose home screen that loads today's improvement tip
through the ViewModel/use-case/repository chain, with loading, success, and error
states, wired through a Material 3 theme with dynamic color on Android 12+.

## Next steps

The structure is ready to grow: add screens as new routes in `ui/navigation`,
new features as `domain` use cases backed by real `data` sources (Room / Retrofit),
and additional Hilt modules for those data sources.
