## Universities List (Kotlin - MVVM - Jetpack Compose)

An android app to show Universities List using [University Domains List API](https://github.com/Hipo/university-domains-list-api). In this App, I lock query to Indonesia only. 

## Features
- University List
  - Showing University Name, University Country, and its Domain website.
  - Offline first data -> hit API first then we save it into local database use Room, then app will show data from local database
  - If the offline data is in different day with current device time -> re-hit API again and re-save it to local database again
- University Domain Web
  - Redirect to University's domain web in in-app webview
- Search
  - Search by University name from local database
  - Minimum search by 3 chars, if query is less than 3 chars then show message
  - If there are no search result then show empty state

## Architecture
  - MVVM Architecture (Model - ComposableView - ViewModel)
  - Repository pattern
  - [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) by implement Usecase pattern, to support separatation between domain and data layer. Also there will be a mapper to map from data to domain.

## Dependency Use
- Retrofit, handle networking
- Room, handle local database
- Dagger Hilt, handle dependency injection
- Jetpack compose
- Coroutine
- Flow
- [SqlCipher](https://github.com/sqlcipher/android-database-sqlcipher), handle local database encryption
- Mockito, JUnit4, [Elymr](https://github.com/xgouchet/Elmyr), etc to handle unit test
- Timber, handle logging
- Coil, handle image loading (in this repo, I dont use it because there are no image that can loaded from API)

## Branches
- feature/setup-domain-layer-for-remote-source => Setup domain layer, especially for remote source
- feature/setup-data-layer-for-remote-source => Setup data layer, especially for remote source
- feature/showing-universities-list => showing universities list and handle search logic
- feature/implement-webview-to-open-link => handle redirection to webview when university's domain clicked
- feature/implement-local-database => implement offline first by save the remote source to local database, handle some logic in the repository
- feature/add-unit-test => create unit test of view model and another business logic
- feature/improvement-and-refactor-some-codes => improve and refactor codes after self-test (fix some issues) and simplify codes
