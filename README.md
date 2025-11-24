# PLAnt capstone

## Introduction

This repository contains the PLAnt capstone Java project. The project is configured to be built and run without Maven using a simple `javac`/`java` workflow.

## Build & Run (no Maven)

-   **Compile:** `./build.sh` (requires a JDK; recommended Java 21)
-   **Run:** `./run.sh`

The scripts compile all `.java` files under `src/main/java` into the `out/` directory and run the `com.plantfarmlogger.Main` class.

### JDK

This project targets Java 21. Install a JDK 21 distribution (Adoptium, Azul, Oracle, or via `sdkman`). After installing, ensure `javac` and `java` on your PATH point to the JDK 21 binaries.



## Project Purpose

The original Plant Farm Logger program helps farmers log and track activities related to plant care (planting dates, watering schedules, fertilizer application, pest control, growth monitoring). This repository preserves that functionality while simplifying the build to plain `javac`/`java` commands.
