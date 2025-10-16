plugins {
    id("com.android.application") version "8.1.1"
    kotlin("android") version "1.9.10"
    kotlin("kapt") version "1.9.10"
}

android {
    namespace = "com.qiaotime.poc"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.qiaotime.poc"
    // Set minSdk to 16 to support Android 4.1+ test devices (Android 16)
    // Note: some modern libraries (e.g. Coil 2.x) require higher API levels, so
    // we use Glide for image loading which maintains compatibility with older APIs.
    minSdk = 16
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildToolsVersion = "36.0.0"

}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    // Use CommonMark to render Markdown to HTML, then display via WebView (works across older APIs)
    implementation("org.commonmark:commonmark:0.21.0")
    implementation("org.commonmark:commonmark-ext-gfm-tables:0.21.0")

    // Coil for image loading (optional, Markwon image-coil brings coil as transitive dep)

    // PhotoView for image zooming
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    // Room (skeleton, optional for full implementation)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
