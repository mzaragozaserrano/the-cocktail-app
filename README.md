# The Cocktail App

Welcome to The Cocktail App, a versatile beverage and cocktail application that showcases two parallel implementations using different architectural approaches. This repository embraces the principles of SOLID and Clean Architecture to ensure a robust and maintainable codebase.

## Features

- **MVI and XML Views**
  - Explore the MVI architecture combined with XML-based views for one version of The Cocktail App.
- **MVVM and Jetpack Compose**
  - Experience the MVVM architecture with Jetpack Compose for a modern and reactive UI in the other version.

## Architecture and Design Principles

- **SOLID Principles**
  - Adheres to SOLID principles to enhance code readability and promote scalability.
- **Clean Architecture**
  - Implements Clean Architecture, emphasizing separation into distinct modules for enhanced modularity and ease of maintenance.

## Module Structure

### 1. **app**
  - **Responsibility:** Main application module orchestrating the integration of the data, domain, and presentation layers.
  - **Access:**
    - Has access to the **data Module** to coordinate data operations and retrieval.
    - Coordinates with the **domain Module** to execute business logic and use cases.
    - Interacts with the **presentation Module** to manage the user interface and presentation logic.

### 2. **data**
  - **Responsibility:** Handles data-related operations and interacts with external sources.
  - **Access:**
    - Has access to the **domain Module** to fetch and transform data based on business logic.
    - Maintains independence from the **presentation Module** to avoid coupling with UI-related concerns.

### 3. **domain**
  - **Responsibility:** Contains the business logic and use cases, serving as the core functionality of The Cocktail App.
  - **Access:**
    - Maintains independence and does not have direct access to other modules.
    - Ensures a separation of concerns, allowing the **data Module** to handle data operations and the **presentation Module** to manage the user interface.

### 4. **presentation**
  - **Responsibility:** Manages the user interface and presentation logic, incorporating Jetpack Compose components and XML views utilizing Material 3 design principles. Notably, certain Jetpack Compose components in use come with animations, adding a dynamic and fresh user experience.
  - **Access:**
    - Has access to the **domain Module** to retrieve and display data based on business logic.
    - Maintains independence from the **data Module** to avoid coupling with data-related operations.
   
## Core Framework

- **Custom Core Framework**
  - Developed by the owner, this framework includes essential modules: `data`, `domain`, and `presentation`. These modules serve as the foundation for various applications, providing base files, utilities, and Jetpack Compose components for efficient app development.
  - The core framework is integrated into The Cocktail App through the inclusion of a repository named `mzs-core`. This repository is implemented in each development project. This approach streamlines the sharing of common functionalities and Jetpack Compose components, contributing to consistent and efficient development across different parts of the application.

## Dependency Injection

- **Koin**
    - Utilizes Koin across all modules for comprehensive dependency injection. This includes the
      creation of ViewModels, Activities/Fragments, UseCases, Repositories, DataSources, and even
      extends to the Tests. This approach promotes modularity and ensures the codebase remains
      testable and maintainable by efficiently managing dependencies throughout the project.
 
## Third-Party Integrations

- **Coil**
  - Utilizes Coil for efficient image loading and display within Jetpack Compose.
- **Coroutines**
  - Implements coroutines for asynchronous programming, enhancing the app's responsiveness.
- **Glide**
  - Employs Glide for seamless image loading and handling in the XML-based version.
- **Lottie**
  - Integrates Lottie for high-quality animations, adding flair to The Cocktail App.
- **Moshi**
  - Uses Moshi for transforming JSON into DTOs for seamless data handling.
- **OkHTTP3**
  - Utilizes OkHTTP3 for creating efficient network operations and handling URL requests.
- **Turbine**
  - Incorporates Turbine for testing MutableStateFlow, following best practices outlined in the [Android Developer documentation](https://developer.android.com/kotlin/flow/test?hl=es-419).

## Getting Started

1. Clone the repository: `git clone https://github.com/mzaragozaserrano/the-cocktail-app.git`
2. Open the project in your preferred IDE.
3. Choose the architecture flavor:
   - For the MVI architecture combined with XML-based views, select the `mvi` flavor.
   - For the MVVM architecture with Jetpack Compose, select the `mvvm` flavor.
4. Build and run the application.

Feel free to explore, contribute, and mix up your favorite drinks with The Cocktail App! Cheers! 🍹🍸🥂
