// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        // Add other repositories if needed
    }
    dependencies {
        // Other dependencies
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7"
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}