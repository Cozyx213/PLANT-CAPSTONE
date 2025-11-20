# PLANT-CAPSTONE
This is a Java Capstone project for Farm Management System

## Overview
This project implements a comprehensive farm management system with support for managing farms, farmers, plants (crops, herbs, shrubs), crop beds, and logging farming activities.

## Project Structure
```
src/main/java/com/farm/
├── Action.java          - Enum for farming actions (WATERED, FERTILIZED)
├── Plant.java           - Abstract base class for all plants
├── Herbs.java           - Herbs plant type
├── Shrubs.java          - Shrubs plant type
├── Crop.java            - Crop plant type with name
├── Bed.java             - Bed dimensions (width, length, height)
├── CropBed.java         - Combination of Crop and Bed
├── Farmer.java          - Farmer information
├── Log.java             - Activity log entry
├── Farm.java            - Main farm class with log management
├── CSVLoader.java       - Utility for loading/saving CSV data
└── Main.java            - Main application entry point

data/
├── farm.csv             - Farm information
├── farmers.csv          - Farmer records
├── crop_beds.csv        - Crop bed records
└── crop_log.csv         - Activity logs
```

## Features
- **Farm Management**: Track farm name, address, farmers, and plants
- **Farmer Management**: Store farmer details (name, age, address)
- **Plant Types**: Support for Crops, Herbs, and Shrubs with inheritance
- **Crop Beds**: Track crop bed dimensions and soil information
- **Activity Logging**: Log farming activities (watering, fertilizing) with status tracking
- **CSV Data Persistence**: Load and save data from/to CSV files

## Compilation
```bash
javac -d bin -sourcepath src/main/java src/main/java/com/farm/*.java
```

## Running
```bash
java -cp bin com.farm.Main
```

## CSV File Formats

### farm.csv
```
FarmName,Address
```

### farmers.csv
```
Name,Age,Address,FarmName
```

### crop_beds.csv
```
CropName,SoilType,LastFertilized,Width,Length,Height,FarmName
```

### crop_log.csv
```
Date,FarmerName,CropName,HealthStatus,GrowthStatus,Action
```

## Example Usage
The Main class demonstrates:
1. Loading farm, farmer, and crop bed data from CSV files
2. Creating new log entries
3. Reading logs from memory
4. Loading historical logs from files

## Class Diagram
```
Farm
├── name: String
├── address: String
├── farmers: List<Farmer>
├── plants: List<Plant>
├── logs: List<Log>
├── createLog(Log): void
└── readLogs(): List<Log>

Farmer
├── name: String
├── age: int
├── address: String
└── farmName: String

Plant (Abstract)
├── soilType: String
└── lastFertilized: String
    ├── Herbs
    ├── Shrubs
    └── Crop
        └── name: String

Bed
├── width: double
├── length: double
└── height: double

CropBed
├── crop: Crop
├── bed: Bed
└── farmName: String

Log
├── date: String
├── farmer: Farmer
├── cropBed: CropBed
├── healthStatus: String
├── growthStatus: String
└── action: Action

Action (Enum)
├── WATERED
└── FERTILIZED
```
