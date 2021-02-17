# TMDB_Client_Android_Application
## Description

We are all bored during this long quarantined season due to the global pandemic outbreak. Now what to do alone in our homes? Simple answer - Binge watch popular Movies and Tv Shows. But we always get overwhelmed by going through the long list of Netflix/Amazon Prime Video/Disney+. Here, our Android application comes in handy. It will suggest you with all the popular Movies and Tv Shows that people are bingeing on in the current times. You can now quickly go through the short-listed suggestions and have yourself a good time! Also, it displays the list of famous celebs with their popularity index.

## Application Screenshots

<img src="images/home.jpg" width="200" height ="400">  <img src="images/movies.jpg" width="200" height ="400">  <img src="images/tv.jpg" width="200" height ="400">  <img src="images/artist.jpg" width="200" height ="400">

## Usage

For installation and usage of this application
Navigate to: [/APK/TMDBClient.apk](https://github.com/SayantanBanerjee16/TMDBClient/tree/main/APK) download and install the application into your android phones. <Recommended Android 8.0(Oreo) version and above>

## Code structure

<img src="images/cleancodearchitecture.png" width="300" height ="600">

This application is created mainly by keeping Clean Code MVVM Architecture into our point of vision. 

Why the cleaner approach?

- Separation of code in different layers with assigned responsibilities making it easier for further modification.
- High level of abstraction
- Loose coupling between the code
- Testing of code is painless

What are the Different Layers of the Project?

- **Data layer:** Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.
  - **Remote Database:** API provides remote networking implementation. Any networking library can be integrated into Android Application using Retrofit. Defining the interfaces and setting up the RetrofitService is done in this layer.
  - **Local Database:** The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. Defining the Room entity, setting up the DAO Interface and building the Database Builder is done in this layer.

- **Domain layer:** This will be the most generic layer of the three. It will connect the presentation layer with the data layer. This is the layer where app-related business logic will be executed. All the application use-cases and the repositories interfaces reside in the domain layer.
  - **Use Cases:** Use cases are the application logic executor. As the name depicts each functionality can have its separate use case. With more granularity of the use case creation, it can be reused more often.
  - **Repositories:** It specifies the functionalities required by the use cases which is implemented by the data layer. 

- **Presentation layer:** The presentation layer provides the UI implementation of the application. This layer internally implements MVVM (Model-View-ViewModel) architecture.
  - Why _MVVM Architecture_ over other patterns: MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables. Also View Model store and manage UI-related data in a lifecycle conscious way. It allows data to survive configuration changes such as screen rotations.

## Technologies

- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For working with asynchronous threading related task.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps. Thus they help us to separate business logic apart from the UI logic and helps us in designing proper architecture.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on configuration changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Dagger-2](https://github.com/google/dagger) - A fast dependency injector for Java and Android. Dagger is a compile-time framework for dependency injection.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android.

## Built With

* Android Studio
* [TMDB API](https://developers.themoviedb.org/3/getting-started/introduction)

## Author

* <a href="https://www.linkedin.com/in/sayantan-banerjee-iiitgwalior/">  **Sayantan Banerjee** </a>
