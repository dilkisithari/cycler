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






