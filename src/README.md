# Financial Area Manager

Individual project developed for Software Development II. Manages three business areas — Investments, Marketing, and Infrastructure — with full CRUD operations, financial analysis, and a dual persistence strategy.

## Features

- Manage investments, marketing strategies, and infrastructure assets
- Real-time budget tracking with deviation and execution percentage per area
- Economic impact analysis with custom bar chart
- Dual persistence: file-based storage, MySQL database, or both simultaneously — selectable at runtime
- Administrator authentication for account and budget configuration
- Custom dark-themed desktop UI built with Java Swing and FlatLaf

## Tech Stack

- Java (Swing + FlatLaf)
- MySQL
- JDBC (manual DAO pattern)
- MVC architecture
- Generic abstract class `Area<T>` for shared business logic across areas
- Custom generic collection `ListaElementos<T>` implementing `Iterable<T>`
- UUID-based entity identification
- Custom exception handling

## Architecture Notes

Database persistence was optional and not yet covered in the course at the time of development — implemented independently alongside file-based persistence. A `TipoPersistencia` enum allows the user to choose between `ARCHIVO`, `BD`, or `AMBOS` at runtime without changing the codebase.

## Requirements
- Java 11+
- MySQL 8+ (create the database manually or use your own schema)
- Copy `config.properties.example` to `config.properties` and fill in your database credentials

## Author

Albertt Mora