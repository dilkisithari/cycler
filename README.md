# 🚲 Cycler

Cycler is an Android app for renting bicycles from shops around Kandy, Sri Lanka. Users can browse rental shops, view them on a map, rent bicycles by the hour and pay online.

## Features

- Sign up / sign in (local account stored on the device, passwords salted and hashed)
- Browse and search bicycle rental shops
- View a shop's location on Google Maps
- Rent one or more bicycles (stock is updated automatically)
- Track ongoing rentals and see the running cost
- Pay and end a rental with PayHere (sandbox)
- Rental / payment history and profile screen

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Java 11 |
| UI | Material Components, View Binding, Navigation Component, RecyclerView |
| Storage | SQLite (`SQLiteOpenHelper`) + SharedPreferences |
| Maps | Google Maps SDK for Android |
| Payments | PayHere Android SDK (sandbox) |
| Images | Glide |
| Build | Gradle (Kotlin DSL), AGP 8.9, `minSdk 24`, `compileSdk 35` |

## Getting started

### Requirements

- Android Studio (latest stable) with Android SDK 35
- JDK 11 or newer (the one bundled with Android Studio works)
- A Google Maps API key
- A PayHere sandbox merchant ID

### 1. Clone

```bash
git clone https://github.com/<your-username>/Cycler.git
cd Cycler
```

### 2. Add your keys (not committed to git)

Copy the example file and fill in your own values:

```bash
cp local.properties local.properties
```

```properties
MAPS_API_KEY=your_google_maps_api_key
PAYHERE_MERCHANT_ID=your_payhere_merchant_id
```

`local.properties` is listed in `.gitignore`, so your keys stay on your machine. Gradle reads them at build time and injects them into the manifest (`${MAPS_API_KEY}`) and `BuildConfig`.

> Tip: restrict your Maps key in Google Cloud Console to your app's package name and SHA-1 fingerprint.

For PayHere sandbox payments, also add your app's package name as an authorised domain/app in the PayHere sandbox dashboard.

### 3. Run

Open the project in Android Studio, let Gradle sync, then press **Run ▶** on an emulator or a device.

Command line:

```bash
./gradlew assembleDebug
```

## Project structure

```
app/src/main/java/lk/nd/cycler/
├── MainActivity.java          # Bottom-navigation host
├── SignUpActivity.java        # Launcher screen / registration
├── SignInActivity.java
├── SingleShopView.java        # Shop details + place rental
├── MapViewActivity.java       # Shop location on Google Maps
├── PayNowActivity.java        # PayHere payment
├── Constants.java             # Reads values from BuildConfig
├── adapter/                   # RecyclerView adapters
├── database/DatabaseHelper.java
├── model/                     # LocationCard, OngoingRental, PaymentHistoryCard
└── ui/                        # home, rentals, profile fragments
```

## Security notes

- Never commit `local.properties`, keystores or API keys.
- The PayHere **merchant secret is not used in the app**. Keep it on a server if you ever need it.
- If a key was ever committed or shared, revoke it and create a new one.

## Author

**YOUR_NAME** — [GitHub](https://github.com/YOUR_GITHUB_USERNAME)

## License

Add a license of your choice (for example MIT) in a `LICENSE` file.
