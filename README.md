# 🚗 FlexDrive (Car Rental App)

>  **Status:** Work In Progress - Active Development

A modern Android application simulating a premium car rental service. This project demonstrates **Clean Architecture**, **MVVM**, and **SOLID principles** using the latest Android tech stack.

## 📱 Features

* **Authentication Flow:**
* Username: `user@example.com`
* Password: `user1234`
* Simulated Login with validation.
* "Remember Me" functionality using **DataStore** (persists credentials for quick access).
* **Modern Dashboard:**
    * Top-Tab navigation (Main, Rent, History).
    * Custom UI components (Pill-shaped tabs, Hero sections).
* **Smart Car Catalog (Rent Tab):**
    * **Hybrid Data:** Combines high-quality local assets (images) with real-time remote data.
    * **Expandable Cards:** View vehicle details, select rental duration via **Date Range Picker**, and calculate costs dynamically.
    * **API Integration:** Fetches technical specifications (Horsepower, Engine, Transmission) from the internet on-demand.
* **AI Digital Assistant:**
    * Powered by Google **Gemini 2.5 Flash**.
    * Integrated chatbot to answer questions about rentals and car models in real-time.
* **Rental History:**
    * Persists completed rentals using **Room Database**.
    * View past transactions with date formatting and total cost.
    * Delete functionality.

## 🛠 Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** Clean Architecture (Data, Domain, Presentation layers) + MVVM
* **Dependency Injection:** Hilt (Dagger)
* **Network:** Retrofit + Moshi + OkHttp
* **Local Storage:**
    * **Room Database:** For saving rental history.
    * **DataStore (Preferences):** For user session management.
* **Concurrency:** Coroutines & Flow
* **Navigation:** Jetpack Compose Navigation

## ⚠️ Setup & API Key Configuration

This application integrates external services to enhance functionality. While the app is fully functional without these keys (it won't crash), configuring them will unlock the full potential of the features.

The app features a Digital Assistant powered by the **Gemini 2.5 Flash** model to answer user questions about car rentals.

* **Setup:**
    1.  Obtain a free API Key from [Google AI Studio](https://ai.google.dev/gemini-api/docs/api-key).
    2.  Open the `local.properties` file in your root directory.
    3.  Add the following line:
        ```properties
        GEMINI_API_KEY="your_api_key_here"
        ```
> **Note:** If this key is missing, the app **will NOT crash**. The Chatbot interface will simply display a system message informing you that the API key is missing, while the rest of the application (Browsing, Renting) remains fully operational.

This app uses the **API Ninjas - Cars API** to fetch vehicle technical specifications (e.g., Engine size, Transmission, Fuel type).

* **Setup:**
1. **Get a Free API Key:** Sign up at [API Ninjas](https://api-ninjas.com/api/cars).
2. **Configure local.properties:**
    Create or open the `local.properties` file in the root directory (this file is ignored by Git for security) and add your key:
    ```properties
    API_NINJAS_KEY="your_api_key_here"
    ```
> **Note:** If you skip this step, the app **will still function correctly**. You will be able to browse cars and rent them, but the specific technical details (Year, Engine, Fuel) will show as "N/A" or "-" inside the expandable cards. The app handles missing API keys gracefully without crashing.

###How to Run
1.  **Clone** the repository.
2.  **Configure** `local.properties` (as shown above).
3.  **Build & Run** via Android Studio.

## 📸 Screenshots
*(Screenshots will be added soon.)*

## 🔜 Roadmap & Future Improvements

This project is actively evolving. Future updates will focus on advanced features:
So far: 

- [x] **Core Architecture:** Setup Hilt, Room, Retrofit, Mappers.
- [x] **Authentication:** Login UI & Logic + DataStore.
- [x] **Rent Feature:** DateRangePicker, Price Calculation, Expandable UI.
- [x] **API Integration:** Connected to Api Ninjas for live data.
- [x] **History Feature:** Database CRUD operations.
- [x] **Payment Simulation:** Add a `Dialog` with a fake payment form before renting.
- [x] **User Profile:** Profile screen with Logout functionality.
- [x] **Info Screen:** FAQ and About
- [x] **Ai digital assistant** Gemini api 
- More to add...

---