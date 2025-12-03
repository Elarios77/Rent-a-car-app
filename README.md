# 🚗 FlexDrive (Car Rental App)

> 🚧 **Status:** Work In Progress (WIP)

A modern Android application simulating a car rental service. This project serves as a practical demonstration of **Clean Architecture** and **MVVM** patterns using the latest Android tech stack.

## 📱 Features
* **Authentication:** Simulated login screen with state handling.
* **Dashboard:** Top-tab navigation with swipe gestures (Home, Rent, History).
* **Car Catalog:** Expandable lists displaying vehicle details.
* **Hybrid Data:** Combines local assets (images) with remote technical specifications.
* **Reactive UI:** Built entirely with Jetpack Compose.

## 🛠 Tech Stack
* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** Clean Architecture (Data, Domain, Presentation layers) + MVVM
* **DI:** Hilt (Dagger)
* **Network:** Retrofit + Moshi
* **Local DB:** Room Database
* **Concurrency:** Coroutines & Flow
* **Navigation:** Navigation Compose

## 🔌 Data Source
Technical specifications for the vehicles are fetched in real-time using the [API Ninjas - Cars API](https://api-ninjas.com/api/cars).

## 🔜 Roadmap / To-Do
- [x] Login Screen & Logic
- [x] Dashboard & Navigation
- [x] Rent Screen (Listing & Details)
- [ ] History Screen Implementation (Room integration)
- [ ] Details and future implementations
