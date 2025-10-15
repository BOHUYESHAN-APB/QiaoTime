// Minimal root build.gradle.kts using buildscript classpath to ensure AGP and Kotlin plugin are available.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
}

// Repository settings for all projects
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
