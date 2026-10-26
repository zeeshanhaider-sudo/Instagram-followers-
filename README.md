# Instagram Following Cleanup — Android APK source

A mobile-first Android app for reviewing Instagram following data locally and opening selected profiles in the installed Instagram app for manual review.

## Features
- Enter your own Instagram username and open your profile.
- Import Instagram's exported `followers_1.json` and `following.json` files.
- Identify accounts that do not follow you back, mutual follows, and followers you do not follow.
- Search accounts, select a capped number for review, and export a text checklist.
- Open each selected profile in Instagram (or a browser fallback) and decide manually whether to unfollow.

## Important limitation
Entering a username alone cannot access an account's live Following list. This app does not log in to Instagram, use passwords/cookies, fetch live followers, or automatically unfollow accounts. The profile-opening action only opens the profile URL; the user must review and confirm any unfollow inside Instagram.

## Build APK using GitHub Actions
1. Open the repository's Actions tab.
2. Open the latest **Build Android APK** run and wait for it to finish successfully.
3. Download the `Instagram-Following-Cleanup-APK` artifact.
4. Extract the ZIP and install `app-debug.apk` on your Android phone.

## Import data
In Instagram Accounts Center, request a download of your information and choose Followers and Following in JSON format. Extract the Instagram ZIP on your phone. In this app select the followers JSON file and following JSON file together. The app currently accepts JSON files, not the ZIP itself.

## Privacy
- No Instagram password, login, session cookies, or Instagram API are used.
- JSON files are processed locally on the phone and are not uploaded to a server.
- The app creates a checklist and opens profile pages; it does not perform unfollows automatically.
- This is a debug APK for personal testing, not Google Play publishing.
