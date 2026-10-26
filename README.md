# Instagram Following Cleanup — Android APK source

This is a mobile-first Android WebView app. It analyses Instagram followers/following JSON exports locally on the phone. It does not ask for Instagram credentials and does not automatically unfollow anyone.

## Build APK using GitHub Actions
1. Open the repository's Actions tab and run **Build Android APK** (or let the workflow start after the files are added).
2. Wait for the workflow to finish successfully.
3. Open the run and download the `Instagram-Following-Cleanup-APK` artifact.
4. Extract the artifact ZIP and install `app-debug.apk` on your Android phone.

## Import data
In Instagram Accounts Center, request a download of your information and choose Followers and Following in JSON format. Extract the Instagram ZIP on your phone. In this app select the followers JSON file and following JSON file together. This initial version accepts JSON files, not the ZIP itself.

## Privacy and limitations
- No Instagram password, login, cookies, or Instagram API are used.
- JSON files are read locally in the app and are not uploaded to a server.
- The app creates a review checklist only; it does not unfollow accounts automatically.
- This is a debug APK for personal testing, not Google Play publishing.
- Install APKs only from sources you trust.