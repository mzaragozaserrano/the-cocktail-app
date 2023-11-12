# The Cocktail App

Welcome to The Cocktail App, a versatile beverage and cocktail application that showcases two parallel implementations using different architectural approaches. This repository embraces the principles of SOLID and Clean Architecture to ensure a robust and maintainable codebase.

## Features

- **MVI and XML Views**
  - Explore the MVI architecture combined with XML-based views for one version of The Cocktail App.
- **MVVM and Jetpack Compose**
  - Experience the MVVM architecture with Jetpack Compose for a modern and reactive UI in the other version.

## Third-Party Integrations

- **Coil (Jetpack Compose)**
  - Utilizes Coil for efficient image loading and display within Jetpack Compose.
- **Glide (XML)**
  - Employs Glide for seamless image loading and handling in the XML-based version.
- **Turbine (Testing)**
  - Incorporates Turbine for testing MutableStateFlow, following best practices outlined in the [Android Developer documentation](https://developer.android.com/kotlin/flow/test?hl=es-419).
- **Coroutines**
  - Implements coroutines for asynchronous programming, enhancing the app's responsiveness.
- **Lottie**
  - Integrates Lottie for high-quality animations, adding flair to The Cocktail App.
- **Moshi and OkHTTP3**
  - Utilizes Moshi for JSON parsing and OkHTTP3 for efficient network operations.

## Architecture and Design Principles

- **SOLID Principles**
  - Adheres to SOLID principles to enhance code readability, maintainability, and scalability.
- **Clean Architecture**
  - Implements Clean Architecture, separating the app into distinct modules for enhanced modularity and maintainability.

## Module Structure

1. **app Module**
   - Main application module orchestrating the integration of the data, domain, and presentation layers.

2. **data Module**
   - Handles data-related operations and interacts with external sources.

3. **domain Module**
   - Contains the business logic and use cases, serving as the core functionality of The Cocktail App.

4. **presentation Module**
   - Manages the user interface and presentation logic, incorporating Jetpack Compose components for the Jetpack Compose version and utilizing Material 3 design principles. Also, uses XML views for the other version.

## Asynchronous Programming

- **Coroutines**
  - Implements coroutines for asynchronous programming, enhancing the app's responsiveness. Details on coroutine usage can be found in a dedicated section or document within the project.

## Dependency Injection

- **Dagger/Hilt**
  - Leverages Dagger/Hilt for efficient dependency injection, promoting modular and testable code.

## Core Framework and Modules

- **Custom Core Framework**
  - Includes three essential modules: mzs-data, mzs-domain, and mzs-presentation. Owned by the developer, these modules provide foundational files and Jetpack Compose components for efficient app development.

## Getting Started

1. Clone the repository: `git clone https://github.com/your-username/the-cocktail-app.git`
2. Open each application module in Android Studio.
3. Build and run the desired version of The Cocktail App.

Feel free to explore, contribute, and mix up your favorite drinks with The Cocktail App! Cheers! 🍹🍸🥂
