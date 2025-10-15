
# Gradle Wrapper helper

This repository includes `gradlew` and `gradlew.bat` launcher scripts and a `gradle/wrapper/gradle-wrapper.properties` that points to Gradle 8.4.

Note: `gradle-wrapper.jar` (binary) is not included in the repository by default in this workspace environment. To obtain it locally, do one of the following:

1. If you have Gradle installed locally, run from the project root:

	# Gradle Wrapper helper

	This repository includes `gradlew` and `gradlew.bat` launcher scripts and a `gradle/wrapper/gradle-wrapper.properties` that points to Gradle 8.4.

	Note: `gradle-wrapper.jar` (binary) is not included in the repository by default in this workspace environment. To obtain it locally, do one of the following:

	1. If you have Gradle installed locally, run from the project root:

	   ```powershell
	   gradle wrapper --gradle-version 8.4
	   ```

	   This will generate `gradle/wrapper/gradle-wrapper.jar` and update wrapper files. Commit the generated `gradle/wrapper/gradle-wrapper.jar` so other developers and CI can run `./gradlew`.

	2. Open the project in Android Studio. When importing, Android Studio will generate the wrapper files and download the Gradle distribution automatically.

	3. If you prefer, I can add the `gradle-wrapper.jar` binary to the repo for you — reply to request that and I'll commit it.

	After you have `gradle-wrapper.jar` in `gradle/wrapper/`, run (Windows PowerShell):

	```powershell
	.\gradlew.bat :app:assembleDebug --stacktrace
	```

	If you run into further issues, paste the `--stacktrace` output here and I will continue diagnosing.
