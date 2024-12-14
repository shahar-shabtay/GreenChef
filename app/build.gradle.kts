plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Make sure this plugin is included
}

android {
    namespace = "com.example.greenchef"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.greenchef"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Room dependencies
    implementation(libs.androidx.room.runtime) // Room runtime
    kapt(libs.androidx.room.compiler)         // Room annotation processor
    implementation(libs.androidx.room.ktx)    // Room KTX for coroutines

    // Other dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Kapt configuration for Room
kapt {
    arguments {
        arg("room.schemaLocation", projectDir.resolve("schemas").absolutePath) // Correct Kotlin DSL syntax
        arg("room.incremental", "true")
    }
}