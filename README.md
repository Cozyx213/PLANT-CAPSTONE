# Plant Farm Logger Program

## Introduction

The **Plant Farm Logger** program is a tool designed for farmers to log and track various activities related to the plants in their crops or farms. By keeping detailed records of plant care and farming activities, users can ensure better management of their crops, improve productivity, and make informed decisions about plant care.

This program allows farmers to easily log daily activities, monitor the health and growth of plants, and maintain a clear history of crop management activities.

## Purpose

The main purpose of the **Plant Farm Logger** program is to help farmers efficiently track the following:

-   Planting dates
-   Watering schedules
-   Fertilizer application
-   Pest control actions
-   Growth monitoring

This logging system aids in building a reliable history of farming practices that can later be analyzed to improve crop yield and health.

## Features

### 1. **Activity Logging**

-   Users can log different types of activities, including planting, watering, fertilization, pest control, and harvesting.
-   Each log entry includes key information such as the date, the type of activity, and any additional notes or observations.

### 2. **Plant Health Tracking**

-   Track the health of plants over time.
-   Log any issues such as diseases, pests, or nutrient deficiencies.
-   Set reminders to regularly monitor plant health and perform necessary actions.

### 3. **Data Export**

-   Export log data to CSV or PDF for further analysis or record-keeping.
-   Share reports with other team members or stakeholders in the farm.

### 4. **Customizable Fields**

-   Customize the log entries to include specific plant varieties or farm-specific parameters, such as soil types or irrigation methods.

## Example Use Case

A farmer growing tomatoes on their farm may use the **Plant Farm Logger** program to:

1. Log the planting date of the tomato seeds.
2. Record the amount of water used daily for irrigation and any notes on weather conditions.
3. Track the application of fertilizers and pest control measures on a weekly basis.
4. Monitor plant growth by measuring plant height and fruit development over time.
5. Set reminders for checking for pests or harvesting the tomatoes once they reach the appropriate size.

By the end of the season, the farmer will have a complete record of activities and observations, which can be analyzed to improve the next planting cycle.

The **Plant Farm Logger** program is a powerful tool designed to help farmers keep detailed records of their farming activities. By simplifying the process of logging and tracking plant-related tasks, it enables farmers to make informed decisions.

## Quick Start

Follow these steps to build and run the project using the included scripts.

-   On Unix-like systems (Linux / macOS)

```bash
chmod +x build_and_run.sh
./build_and_run.sh
```
-  On Windows

```bash
powershell -ExecutionPolicy ByPass -File ".\build_and_run.ps1"
```

it Compiles all Java sources into `out/`:
and Runs the program (runs `com.plantfarmlogger.Main`):

## How the Program Fulfills the Criteria

### Classes and Objects (25%)

-   Core domain: `Crop` base class with subclasses `HerbCrop`, `LeafCrop`, `RootCrop`.
-   Data access: `CropDao`, `CropLogDao`, `UserDao` manage in-memory lists and CSV persistence.
-   Controllers: e.g., `CropController`, `UserController` orchestrate between views and DAOs.
-   Views: Swing panels/windows (`Login`, `Register`, `Home`, `SideBar`, `CropCardPanel`, `LogCardPanel`, etc.).

### Class Diagram (15%)

-   Recommended inclusions (aligns with current code):
    -   `Crop` ← `HerbCrop` | `LeafCrop` | `RootCrop`
    -   Interfaces: `Prunable`, `Medicinal`, `Subterranean`, `CropDaoInter`, `UserDaoInter`
    -   DAOs: `CropDao`, `CropLogDao`, `UserDao`
    -   Controllers: `CropController`, `UserController`
    -   Views: `MainWindow`, `Home`, `Login`, `Register`, `SideBar`, `CropCardPanel`, `LogCardPanel`

### Four OOP Principles (30%)

-   **Inheritance:** `HerbCrop`, `LeafCrop`, `RootCrop` extend `Crop` to reuse shared dimensions and planting metadata.
-   **Abstraction:** Interfaces (`Prunable`, `Medicinal`, `Subterranean`, `CropDaoInter`, `UserDaoInter`) define required behaviors without exposing implementation.
-   **Encapsulation:** Fields in models/DAOs are private with getters/setters (e.g., `User`, `Crop`, `UserDao`’s list); access controlled through methods.
-   **Polymorphism:** Collections of `Crop` hold subclass instances; DAOs/controllers and UI components operate on `Crop` references and dispatch behavior based on runtime type.

### Exception Handling (10%)

-   CSV loading/writing wrapped in try/catch in DAOs (`CropDao`, `CropLogDao`, `UserDao`), with user-facing error prints.
-   Parsing helpers (e.g., `parseIntOrDefault`) guard against malformed numeric fields.

### File Handling (5% + bonus 5%)

-   Data persisted to CSV under `src/main/resources/csv` (`users.csv`, `crops.csv`, `cropbeds.csv`, `croplogs.csv`).
-   On startup, `Main` ensures the CSV directory/files exist and copies resources to `out/` so runtime has access.

### Graphical User Interface (15%)

-   Swing-based UI with custom styling (`UIColors`, `UIFont`, `UIButtons`, `UIFields`).
-   Screens: `Login`, `Register`, `Home` dashboard with crop cards and sidebar navigation, log views.
-   Gradient backgrounds, rounded inputs/buttons, and logo/plant icons from `resources/png`.

### Design Pattern (bonus 5%)

-   **Singleton:** Controllers (e.g., `UserController`, `CropController`) expose a `getInstance()` to share state across views.
-   **Factory (planned/extendable):** A `CropFactory` can be used to centralize `Crop` creation based on type strings from CSV/input (add here if implemented in codebase).

