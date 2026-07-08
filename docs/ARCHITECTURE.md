# Architecture

Your Daily Improvement uses a layered architecture with **MVVM** in the UI layer
and a strict, one-directional dependency rule:

```
        ┌─────────────────────────────────────────────┐
        │  ui  (Compose screens, components, ViewModel) │
        └───────────────────────┬─────────────────────┘
                                 │ depends on
                                 ▼
        ┌─────────────────────────────────────────────┐
        │  domain  (models, use cases, repo interfaces) │  ← no Android/framework deps
        └───────────────────────▲─────────────────────┘
                                 │ implements
        ┌───────────────────────┴─────────────────────┐
        │  data  (repository impls, local data sources) │
        └─────────────────────────────────────────────┘
```

**The dependency rule:** `ui → domain ← data`. The domain layer is the stable
core: it has **no Android and no framework dependencies** — the only annotation
it uses is the standard JSR-330 `@Inject` (`javax.inject`), a plain
compile-time annotation, not Android or Hilt. UI and data both depend on domain;
they never depend on each other. This keeps business logic testable in plain JVM
tests and lets any layer be replaced in isolation.

## Package structure

```
com.yourdailyimprovement.androidapp/
├── YourDailyImprovementApp.kt      # @HiltAndroidApp Application
├── MainActivity.kt                 # Single-activity host
├── di/                             # Hilt modules (wiring only)
│   └── RepositoryModule.kt         #   binds domain interfaces → data impls
├── data/                           # DATA LAYER
│   ├── local/                      #   local data sources (in-memory today; Room/DataStore later)
│   │   └── ImprovementLocalDataSource.kt
│   └── repository/                 #   repository implementations
│       └── ImprovementRepositoryImpl.kt
├── domain/                         # DOMAIN LAYER (pure Kotlin)
│   ├── model/                      #   domain models
│   │   └── ImprovementTip.kt
│   ├── repository/                 #   repository interfaces (contracts)
│   │   └── ImprovementRepository.kt
│   └── usecase/                    #   one class per unit of business logic
│       └── GetDailyTipUseCase.kt
├── ui/                             # PRESENTATION LAYER (Compose + MVVM)
│   ├── screens/                    #   one package per screen (UI + ViewModel + UiState)
│   │   └── home/
│   │       ├── HomeScreen.kt       #     stateful + stateless composables
│   │       ├── HomeViewModel.kt    #     @HiltViewModel, exposes StateFlow<UiState>
│   │       └── HomeUiState.kt      #     sealed UI state
│   ├── components/                 #   reusable, screen-agnostic composables
│   │   └── TipCard.kt
│   └── theme/                      #   Material 3 theme (Color, Type, Theme)
└── navigation/                     # app-level navigation graph
    ├── AppNavHost.kt               #   NavHost with all destinations
    └── Destinations.kt             #   route constants
```

> `di/` and `domain/repository/` are not in the original proposed diagram but are
> required: Hilt needs a module to bind interfaces to implementations, and the
> repository *interface* belongs in the domain layer (the *implementation* lives
> in `data/`).

## Data flow (the current vertical slice)

Loading today's tip runs through every layer exactly once:

```
HomeScreen (collects StateFlow)
   └─ HomeViewModel  ── calls ──▶ GetDailyTipUseCase   (domain)
                                     └─ ImprovementRepository (interface, domain)
                                          └─ ImprovementRepositoryImpl (data)
                                               └─ ImprovementLocalDataSource (data/local)
```

- The **ViewModel** exposes a single immutable `HomeUiState`
  (`Loading` / `Success` / `Error`) as a `StateFlow`; the UI is a pure function
  of that state.
- The **use case** is the only domain entry point the ViewModel talks to.
- The **repository** decides *which* tip to show; the **data source** decides
  *where* tips come from.

## Dependency injection (Hilt)

- `YourDailyImprovementApp` is annotated `@HiltAndroidApp`.
- `MainActivity` is `@AndroidEntryPoint`; `HomeViewModel` is `@HiltViewModel`
  and obtained in Compose via `hiltViewModel()`.
- `di/RepositoryModule` binds `ImprovementRepository` → `ImprovementRepositoryImpl`.
  Classes with an `@Inject` constructor (use cases, data sources) are provided
  automatically with no extra wiring.

## Where new features go

| To add… | Put it in… |
| --- | --- |
| A new screen | `ui/screens/<feature>/` (Screen + ViewModel + UiState) + a route in `navigation/` |
| A reusable widget | `ui/components/` |
| New business logic | a use case in `domain/usecase/` |
| A new entity | `domain/model/` |
| A new data source (API/DB) | `data/local/` or a new `data/remote/`, behind a `domain/repository/` interface |
| Wiring a new implementation | a `@Binds`/`@Provides` in `di/` |

## Testing

The domain layer is plain Kotlin, so it is unit-testable without Android or Hilt.
See `GetDailyTipUseCaseTest`, which injects a fake `ImprovementRepository` into
the real use case. Stateless composables like `HomeContent` and `TipCard` take
plain state + callbacks, so they are straightforward to preview and UI-test.
