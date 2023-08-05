# AssigAisle

## My Answer to first project

Save users timezone information with their profile data on server

Use some backend tool/technique to group users by time zones into 24 segments, one for each timezone hour. Then, you could have 24 separate processes that run at 12:00 pm in each timezone
and run a refresh every hours for the respective timezones this way we can save quering user db multiple time in a hour


Whenever user opens the app we will sync the latest likes count from our server

Even app can request server for refresh by maintaining a last refresh timestamp and with the help of alarmManager or workManager by  calling refreshLikes endpoint at 12:00 pm according to their logic this is not a good approach generally this refresh logic should be on servers side not on client side as client can try tricks to get more free like by manipulating client clocks and other ways

## Demo


https://github.com/shubham423/AssigAisle/assets/57858666/6c783621-b940-4f7f-b93b-d462e402ad63



## Built With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [StateFlow](https://developer.android.com/topic/libraries/architecture/livedata) - tate-holder observable flow that emits the current and new state updates to its collectors.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
dataset from local storage or over network.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
