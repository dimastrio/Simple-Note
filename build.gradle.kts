// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()  // Google's Maven repository

        mavenCentral()  // Maven Central repository


    }
    dependencies {
        // Dagger Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")

    }
}
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.google.dagger.hilt.android") version "2.42" apply false
    id("com.android.library") version "7.4.1" apply false

}
