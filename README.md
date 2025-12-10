# 🚗 FlexDrive (Car Rental App)

> 🚧 **Status:** Work In Progress (WIP) - Active Development

A modern Android application simulating a premium car rental service. This project demonstrates **Clean Architecture**, **MVVM**, and **SOLID principles** using the latest Android tech stack.

## 📱 Features

* **Authentication Flow:**
* Username: user@example.com
* Password: user1234
* Simulated Login with validation.
* "Remember Me" functionality using **DataStore** (persists credentials for quick access).
* **Modern Dashboard:**
    * Swipeable Top-Tab navigation (Main, Rent, History).
    * Custom UI components (Pill-shaped tabs, Hero sections).
* **Smart Car Catalog (Rent Tab):**
    * **Hybrid Data:** Combines high-quality local assets (images) with real-time remote data.
    * **Expandable Cards:** View vehicle details, select rental duration via **Date Range Picker**, and calculate costs dynamically.
    * **API Integration:** Fetches technical specifications (Horsepower, Engine, Transmission) from the internet on-demand.
* **Rental History:**
    * Persists completed rentals using **Room Database**.
    * View past transactions with date formatting and total cost.
    * Delete functionality.

## 🛠 Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** Clean Architecture (Data, Domain, Presentation layers) + MVVM
* **Dependency Injection:** Hilt (Dagger)
* **Network:** Retrofit + Moshi (w/ Codegen)
* **Local Storage:**
    * **Room Database:** For saving rental history.
    * **DataStore (Preferences):** For user session management.
* **Concurrency:** Coroutines & Flow
* **Navigation:** Jetpack Compose Navigation

## ⚠️ Setup & API Key Configuration

This app uses the **API Ninjas - Cars API** to fetch vehicle technical specifications (e.g., Engine size, Transmission, Fuel type).

### How to run it:
1.  **Clone** the repository.
2.  **Get a Free API Key:** Sign up at [API Ninjas](https://api-ninjas.com/api/cars).
3.  **Configure local.properties:**
    Create or open the `local.properties` file in the root directory (this file is ignored by Git for security) and add your key:
    ```properties
    API_NINJAS_KEY="your_api_key_here"
    ```
4.  **Build & Run!**

> **Note:** If you skip this step, the app **will still function correctly**. You will be able to browse cars and rent them, but the specific technical details (Year, Engine, Fuel) will show as "N/A" or "-" inside the expandable cards. The app handles missing API keys gracefully without crashing.

## 📸 Screenshots
*(Screenshots showcasing the Login, Dashboard, and Rent flows will be added soon.)*

## 🔜 Roadmap & Future Improvements

This project is actively evolving. Future updates will focus on "Professional Polish" and advanced features:

- [x] **Core Architecture:** Setup Hilt, Room, Retrofit, Mappers.
- [x] **Authentication:** Login UI & Logic + DataStore.
- [x] **Rent Feature:** DateRangePicker, Price Calculation, Expandable UI.
- [x] **History Feature:** Database CRUD operations.
- [x] **Payment Simulation:** Add a `Dialog` with a fake payment form before renting.
- [ ] **User Profile:** Profile screen with Logout functionality.
- [ ] **UI Polish:** Add Dark Mode support and animations.
- [ ] **Unit Tests:** Testing UseCases and ViewModels.

---