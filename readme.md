# 💘 Love Vouchers

A minimalist Android app that displays personalized Valentine’s Day vouchers.
This app was originally built as a personal Valentine’s gift for my partner, with the intention to possibly expand and evolve it further over time.
Each voucher can be redeemed exactly once and is permanently marked as **Already Redeemed** after explicit user confirmation.

The app works entirely offline (so far) and stores all data locally on the device.

---

## ✨ Features

- ❤️ & 💙 Two voucher types (In-Person / Remote)
- 🔍 Filter vouchers by type
- ✅ Explicit confirmation before redemption
- 💾 Persistent redemption state (DataStore)
- 📱 Scrollable, modern voucher card layout
- 📴 Fully offline
- 🚫 No login
- 🚫 No backend
- 🚫 No internet connection required
- 📦 Distributed as signed APK (sideloading only)

---

## 🧠 Concept

The app distinguishes between two emotional contexts:

- ❤️ **In-Person vouchers** — for physical closeness
- 💙 **Remote vouchers** — for long-distance connection

A dual color system subtly reinforces this distinction while maintaining a calm, elegant aesthetic.

---

## 🏗 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | Single Activity / Single Screen |
| State Management | Compose State |
| Persistence | DataStore (local key-value) |
| IDE | Android Studio (macOS) |
| Testing | Android Emulator |
| Distribution | Signed APK |


## 🎨 Screenshot

---

## 🧩 Edge Cases

- Double click → prevented via confirmation dialog
- Filter switching → no state loss
- App restart → redemption state preserved
- App reinstall → reset (acceptable)

---

## 🚀 Installation (APK)

1. Enable **“Install unknown apps”** on Android device
2. Transfer signed APK to device
3. Install APK
4. Launch app

No Play Store distribution.

---

## 🔮 Future Enhancements

- (coming soon)

---


## 📦 Deliverables

- Android Studio Project
- Signed APK file
- Installation instructions  