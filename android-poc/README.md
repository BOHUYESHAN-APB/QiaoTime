# QiaoTime Android POC

This is a minimal Android Kotlin POC to demonstrate rendering a Markdown file from `assets/recipes/sample/sample.md` using Markwon and Coil for images.

How to open:

1. Open `android-poc` in Android Studio (File -> Open) and let Gradle sync.
2. Adjust Android SDK/AGP/Kotlin versions in `build.gradle.kts` if necessary.
3. Run the app on an emulator or device. The sample markdown will render in the main activity.

Notes:

- The project is intentionally minimal. For production, add Room entities/DAOs, proper image caching, error handling, and unit/UI tests.
- If you prefer Jetpack Compose, we can produce a Compose variant.

New in this POC:

- On first run the app imports `assets/recipes/sample/sample.md` into a minimal Room database (recipes table).
- The main screen shows a searchable list of recipe titles. Tap a title to render its markdown inline.
- A simple search box performs title LIKE searches against Room (POC implementation).
- Image preview activity is included; images referenced in markdown located in `assets/` can be opened in full-screen via PhotoView (Coil is used to load images from assets URI `file:///android_asset/...`).

Import behavior:

- On first run the app now scans `assets/recipes/` recursively and imports all `.md` files as recipes. The importer uses the file name (without extension) as the recipe title and looks for a sibling `image.jpg` as a cover if present.


Final verification and build notes:

1. Build and run

Open the project in Android Studio and let Gradle sync. If AGP/Kotlin SDK versions mismatch, update the versions in `build.gradle.kts` to match your local SDK and Gradle plugin.

To build from command line (Windows Pwsh), run:

```powershell
./gradlew :app:assembleDebug
```


## Verify import & list

On first run the app imports all markdown files under `assets/recipes/` into Room. The main screen shows the imported recipe titles in a RecyclerView.

## Verify FTS search

Enter a partial title (e.g., `Sample`) in the 搜索 input and press the 搜索 button. The app uses Room FTS for prefix matching; results are returned from the FTS table (fallbacks to LIKE if FTS fails).

## Verify image click preview

Place an `image.jpg` next to your `.md` in the same assets folder (for example `assets/recipes/sample/image.jpg`). Open the recipe in the app and click the rendered image — it should open the full-screen preview (PhotoView).

## Notes & next steps

This is a POC. Before production consider: robust parsing of markdown front-matter, proper Room migrations (avoid destructive fallback), image caching, RecyclerView UI polish, and test coverage.



Run checks:

1. Launch app and confirm a "Sample Recipe" appears in the list.
2. Tap the title to render the markdown in-place (image placeholder references will not show unless image exists in assets as `image.jpg`).
3. Test search by entering "Sample" and pressing 搜索.

Limitations:

- This is a minimal POC focused on demonstrating the data flow. For production you should:

  - Add proper error handling, migration strategies for Room, and robust image caching.

  - Implement FTS-based search instead of naive LIKE if you need richer search.

  - Add unit and instrumentation tests.
